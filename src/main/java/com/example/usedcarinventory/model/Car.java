package com.example.usedcarinventory.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;


@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 連番で自動生成
    private Long id; // 車両管理ID

    // 車両の基本情報
    @NotBlank(message = "車台番号は必須です")
    private String chassisNumber; // 車台番号（ユニークだが主キーではない）

    @NotBlank(message = "車種は必須です")
    private String model; // 車種

    @NotBlank(message = "メーカー名は必須です")
    private String manufacturer; // メーカー名

    @NotBlank(message = "型式は必須です")
    private String vehicleType; // 型式

    @NotNull(message = "初度登録年月は必須です")
    @PastOrPresent(message = "初度登録年月は過去または今日でなければなりません")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate firstRegistration; // 初度登録年月

    @NotNull(message = "車検満了日は必須です")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inspectionExpiry; // 車検満了日

    @Min(value = 0, message = "走行距離は0以上でなければなりません")
    private int mileage; // 走行距離

    // 入庫情報
    @PastOrPresent(message = "入庫日は過去または今日でなければなりません")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate stockDate; // 入庫日

    @NotNull(message = "入庫予定日は必須です")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate scheduledStockDate; // 入庫予定日

    @NotBlank(message = "入庫区分は必須です")
    private String stockType; // 入庫区分（下取り、買取、オークション）

    @NotBlank(message = "仕入先名称は必須です")
    private String supplierName; // 仕入先名称

    @NotBlank(message = "仕入担当者は必須です")
    private String buyerStaff; // 仕入担当者

    // 販売情報
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractDate; // 契約日（任意）
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate transferDate; // 名義変更日（任意）
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate; // 納車日（任意）

    private String saleType; // 販売区分（小売、業販、オークション、廃車）（任意）

    private String buyerName; // 販売先名称（任意）

    private String sellerStaff; // 販売担当者（任意）

    // 価格情報
    @Min(value = 0, message = "車両本体原価は0以上でなければなりません")
    private double baseCost; // 車両本体原価

    @Min(value = 0, message = "リサイクル預託金は0以上でなければなりません")
    private double recycleFee; // リサイクル預託金

    @Min(value = 0, message = "輸送費は0以上でなければなりません")
    private double transportFee; // 輸送費

    @Min(value = 0, message = "加修費は0以上でなければなりません")
    private double repairFee; // 加修費

    @Min(value = 0, message = "販売価格は0以上でなければなりません")
    private double salePrice; // 販売価格

    private double grossProfit; // 粗利（計算フィールド）
    
    private double totalCost; //仕入原価
}