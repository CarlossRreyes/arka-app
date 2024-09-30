package com.arka.app_services.dtos.admin.update;



import com.arka.app_services.dtos.admin.create.CategoryCreateDto;

import lombok.*;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CategoryUpdateDto extends CategoryCreateDto {

    
    public CategoryUpdateDto(String code, String business_id, String name, String description, String image_path, Boolean state,
            String category_id) {
        super(code, business_id, name, description, image_path, state);        
    }



}
