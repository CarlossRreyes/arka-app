package com.arka.app_services.dtos.admin.create;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPricingCreateDto implements Serializable {


    private String product_id;

    private BigDecimal base_price;

    private Boolean is_current;

    private Boolean is_active;
    

}
