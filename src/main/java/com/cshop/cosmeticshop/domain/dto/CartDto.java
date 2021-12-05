package com.cshop.cosmeticshop.domain.dto;

import com.cshop.cosmeticshop.domain.intity.Treatment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private Long id;

    private Long totalPrice;

    private List<Treatment> treatments = new ArrayList<>();
}
