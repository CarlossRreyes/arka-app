package com.arka.app_services.dtos.admin.update;

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
public class ProductPricingUpdateDto implements Serializable {

    private BigDecimal base_price;

    private Boolean state;

}
