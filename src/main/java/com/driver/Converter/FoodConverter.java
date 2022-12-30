package com.driver.Converter;

import com.driver.io.entity.FoodEntity;
import com.driver.shared.dto.FoodDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FoodConverter {
    public static FoodDto convertEntityToDto(FoodEntity foodEntity){
        return FoodDto.builder().id(foodEntity.getId()).foodId(foodEntity.getFoodId()).foodCategory(foodEntity.getFoodCategory())
                .foodName(foodEntity.getFoodName()).foodPrice(foodEntity.getFoodPrice()).build();
    }
}
