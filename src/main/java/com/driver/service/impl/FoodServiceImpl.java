package com.driver.service.impl;

import com.driver.Converter.FoodConverter;
import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FoodServiceImpl implements FoodService{
    @Autowired
    FoodRepository foodRepository;
    @Override
    public FoodDto createFood(FoodDto food) {
        String foodId= UUID.randomUUID().toString();
        FoodEntity foodEntity=FoodEntity.builder().foodId(foodId).foodCategory(food.getFoodCategory())
                        .foodName(food.getFoodName()).foodPrice(food.getFoodPrice()).build();
        foodRepository.save(foodEntity);
        return food;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity foodEntity=foodRepository.findByFoodId(foodId);
        if(foodEntity==null){
            System.out.println("Food Entity doesn't exist");
            return null;
        }
        FoodDto foodDto=FoodDto.builder().id(foodEntity.getId()).foodId(foodEntity.getFoodId()).foodCategory(foodEntity.getFoodCategory())
                .foodName(foodEntity.getFoodName()).foodPrice(foodEntity.getFoodPrice()).build();
        return foodDto;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        FoodEntity foodEntity=foodRepository.findByFoodId(foodId);
        if(foodEntity==null){
            System.out.println("Food Entity doesn't exist");
            return null;
        }
        FoodDto foodDto=foodDetails;
        foodDto.setId(foodEntity.getId());
        FoodEntity food = FoodEntity.builder().id(foodEntity.getId()).foodName(foodDetails.getFoodName()).foodCategory(foodDetails.getFoodCategory())
                .foodPrice(foodDetails.getFoodPrice()).foodId(foodId).build();
        foodRepository.save(food);
        return foodDto;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        FoodEntity foodEntity=foodRepository.findByFoodId(id);
        if(foodEntity==null) System.out.println("Food Entity doesn't exist");
        foodRepository.delete(foodEntity);
    }

    @Override
    public List<FoodDto> getFoods() {
        List<FoodEntity> foodEntities= (List<FoodEntity>) foodRepository.findAll();
        List<FoodDto> foodDtos  = new ArrayList<>();
        for(FoodEntity food:foodEntities){
            foodDtos.add(FoodConverter.convertEntityToDto(food));
        }
        return foodDtos;
    }
}