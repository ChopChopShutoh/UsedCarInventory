package com.example.usedcarinventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.usedcarinventory.model.Car;
import com.example.usedcarinventory.service.CarService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    // 一覧表示
    @GetMapping
    public String listCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "car_list";
    }

    // 新規作成フォーム
    @GetMapping("/new")
    public String newCarForm(Model model) {
        model.addAttribute("car", new Car());
        return "car_form";
    }

    // 編集フォーム
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Car car = carService.getCarById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        model.addAttribute("car", car);
        return "car_form";
        }

    // 保存
    @PostMapping
    public String saveCar(@Valid @ModelAttribute("car") Car car, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "car_form"; // エラーがある場合、フォームに戻る
        }
        carService.saveCar(car);
        redirectAttributes.addFlashAttribute("message", "保存しました");
        return "redirect:/cars";
    }

    // 削除
    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        carService.deleteCar(id);
        redirectAttributes.addFlashAttribute("message", "車両を削除しました");
        return "redirect:/cars";
    }
}