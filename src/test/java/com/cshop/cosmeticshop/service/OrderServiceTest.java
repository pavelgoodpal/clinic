package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Cart;
import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.Treatment;
import com.cshop.cosmeticshop.domain.entity.constants.CartStatus;
import com.cshop.cosmeticshop.exception.DoctorTimePeriodIsBusyException;
import com.cshop.cosmeticshop.repository.OrderRepository;
import com.cshop.cosmeticshop.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Test
    public void correctSaveOrderTest() {
        var startAt = LocalDateTime.now();
        var finishAt = startAt.plusMinutes(5L);

        Treatment treatment = new Treatment();
        treatment.setTreatmentTime(5L);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setStatus(CartStatus.DONE);
        cart.setTreatments(List.of(treatment));

        var order = new Order();
        order.setId(1L);
        order.setStartAt(startAt);

        var orderRepoMock = mock(OrderRepository.class);
        when(orderRepoMock.save(any())).thenReturn(order);

        var cartServiceMock = mock(CartService.class);
        when(cartServiceMock.updateToNoActiveCart(any())).thenReturn(cart);

        var outBoxServiceMock = mock(OutBoxService.class);
        when(outBoxServiceMock.buildOrderEmail(any())).thenReturn(null);

        var doctorScheduleServiceMock = mock(DoctorScheduleService.class);
        when(doctorScheduleServiceMock.addTreatmentPeriodFromOrderToDayOfWeek(order)).thenReturn(true);

        var orderService = new OrderServiceImpl(
                orderRepoMock,
                cartServiceMock,
                outBoxServiceMock,
                doctorScheduleServiceMock
        );

        var savedOrder = orderService.saveOrder(order, cart);
        assertEquals(finishAt, savedOrder.getFinishAt());
    }


    @Test
    public void throwErrorSaveOrderTest() {
        var startAt = LocalDateTime.now();

        var doctor = new Doctor();
        doctor.setLastName("Test");

        Treatment treatment = new Treatment();
        treatment.setTreatmentTime(5L);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setStatus(CartStatus.DONE);
        cart.setTreatments(List.of(treatment));

        var order = new Order();
        order.setId(1L);
        order.setStartAt(startAt);
        order.setDoctor(doctor);

        var orderRepoMock = mock(OrderRepository.class);

        var cartServiceMock = mock(CartService.class);
        when(cartServiceMock.updateToNoActiveCart(any())).thenReturn(cart);

        var outBoxServiceMock = mock(OutBoxService.class);

        var doctorScheduleServiceMock = mock(DoctorScheduleService.class);
        when(doctorScheduleServiceMock.addTreatmentPeriodFromOrderToDayOfWeek(order)).thenReturn(false);

        var orderService = new OrderServiceImpl(
                orderRepoMock,
                cartServiceMock,
                outBoxServiceMock,
                doctorScheduleServiceMock
        );

        try {
            orderService.saveOrder(order, cart);
        } catch (Exception e) {
            assertTrue(e instanceof DoctorTimePeriodIsBusyException);
            return;
        }
        throw new RuntimeException("orderService.saveOrder method should throw Exception");
    }
}
