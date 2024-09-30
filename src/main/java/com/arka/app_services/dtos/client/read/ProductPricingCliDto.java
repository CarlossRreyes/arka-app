package com.arka.app_services.dtos.client.read;

import java.math.BigDecimal;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPricingCliDto {

    private String product_pricing_id;
    private String product_id;
    private BigDecimal base_price;
    private Boolean is_current;
    private Boolean is_active;

}
