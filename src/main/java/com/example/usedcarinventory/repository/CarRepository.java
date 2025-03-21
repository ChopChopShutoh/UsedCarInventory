package com.example.usedcarinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usedcarinventory.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}