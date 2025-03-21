package com.example.usedcarinventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usedcarinventory.model.Car;
import com.example.usedcarinventory.repository.CarRepository;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public Car saveCar(Car car) {
        // 粗利計算
        double totalCost = car.getBaseCost() + car.getRecycleFee() + 
                          car.getTransportFee() + car.getRepairFee();
        car.setTotalCost(totalCost);
        car.setGrossProfit(car.getSalePrice() - totalCost);
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}