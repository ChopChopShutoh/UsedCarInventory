package com.example.usedcarinventory;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.usedcarinventory.model.Car;
import com.example.usedcarinventory.service.CarService;

@Component
public class InitialDataLoader implements CommandLineRunner {

    @Autowired
    private CarService carService;

    @Override
    public void run(String... args) throws Exception {
        // 既存データがない場合のみ初期データを挿入
        if (carService.getAllCars().isEmpty()) {
            List<Car> initialCars = List.of(
                createSampleCar(
                    "1HGCM82633A004352", "シビック", "ホンダ", "DBA-FK2",
                    LocalDate.of(2020, 5, 15), LocalDate.of(2025, 5, 14), 35000,
                    LocalDate.of(2023, 1, 10), LocalDate.of(2023, 1, 5), "下取り",
                    "田中自動車", "山田太郎",
                    LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 15), LocalDate.of(2023, 6, 20),
                    "小売", "佐藤次郎", "鈴木一郎",
                    1000000, 50000, 30000, 20000, 1300000
                ),
                createSampleCar(
                    "2T1BR32E76C123456", "カローラ", "トヨタ", "5BA-MZEA17",
                    LocalDate.of(2019, 8, 20), LocalDate.of(2024, 8, 19), 45000,
                    LocalDate.of(2023, 2, 15), LocalDate.of(2023, 2, 10), "買取",
                    "中古車オークション", "高橋健太",
                    null, null, null,
                    null, null, null,
                    800000, 40000, 25000, 15000, 950000
                ),
                createSampleCar(
                    "JN1AZ34D56M123789", "スカイライン", "ニッサン", "DAA-HNV37",
                    LocalDate.of(2021, 3, 10), LocalDate.of(2026, 3, 9), 25000,
                    LocalDate.of(2023, 3, 20), LocalDate.of(2023, 3, 15), "オークション",
                    "オークション会場A", "斎藤真一",
                    LocalDate.of(2023, 7, 10), LocalDate.of(2023, 7, 20), LocalDate.of(2023, 7, 25),
                    "業販", "株式会社山本", "松本大輔",
                    1500000, 60000, 35000, 25000, 1800000
                )
            );

            initialCars.forEach(carService::saveCar); // 粗利計算を含めて保存
        }
    }

    private Car createSampleCar(
            String chassisNumber, String model, String manufacturer, String vehicleType,
            LocalDate firstRegistration, LocalDate inspectionExpiry, int mileage,
            LocalDate stockDate, LocalDate scheduledStockDate, String stockType,
            String supplierName, String buyerStaff,
            LocalDate contractDate, LocalDate transferDate, LocalDate deliveryDate,
            String saleType, String buyerName, String sellerStaff,
            double baseCost, double recycleFee, double transportFee, double repairFee, double salePrice) {
        Car car = new Car();
        car.setChassisNumber(chassisNumber);
        car.setModel(model);
        car.setManufacturer(manufacturer);
        car.setVehicleType(vehicleType);
        car.setFirstRegistration(firstRegistration);
        car.setInspectionExpiry(inspectionExpiry);
        car.setMileage(mileage);
        car.setStockDate(stockDate);
        car.setScheduledStockDate(scheduledStockDate);
        car.setStockType(stockType);
        car.setSupplierName(supplierName);
        car.setBuyerStaff(buyerStaff);
        car.setContractDate(contractDate);
        car.setTransferDate(transferDate);
        car.setDeliveryDate(deliveryDate);
        car.setSaleType(saleType);
        car.setBuyerName(buyerName);
        car.setSellerStaff(sellerStaff);
        car.setBaseCost(baseCost);
        car.setRecycleFee(recycleFee);
        car.setTransportFee(transportFee);
        car.setRepairFee(repairFee);
        car.setSalePrice(salePrice);
        return car;
    }
}