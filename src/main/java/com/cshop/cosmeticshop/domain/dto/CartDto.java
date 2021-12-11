package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.entity.Treatment;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO class for Cart
 * @author Pave1Pal
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private Long id;

    private Long totalPrice;

    @NotEmpty(message = "choose treatment")
    private List<Treatment> treatments = new ArrayList<>();
}
