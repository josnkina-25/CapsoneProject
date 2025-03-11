package com.josias.empoweridd.controller;

import com.josias.empoweridd.model.CareTaker;
import com.josias.empoweridd.service.CareTakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/caretakers")
public class CareTakerController {
    @Autowired
    private CareTakerService careTakerService;
    @GetMapping
    public String listCareTakers(Model model) {
        model.addAttribute("caretakers", careTakerService.getAllCaretakers());
        return "caretaker/list";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("careTaker", new CareTaker());
        return "caretaker/add";
    }
    @PostMapping("/add")
    public String addCareTaker(@ModelAttribute CareTaker careTaker) {
        careTakerService.createCareTaker(careTaker);
        return "redirect:/caretakers";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        CareTaker careTaker = careTakerService.getCaretakerById(id).orElse(new CareTaker());
        model.addAttribute("careTaker", careTaker);
        return "caretaker/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateCareTaker(@PathVariable Long id, @ModelAttribute  CareTaker careTaker) {
        careTakerService.updateCareTaker(careTaker, id);
        return "redirect:/caretakers";
    }
    @GetMapping("/delete/{id}")
    public String deleteCareTaker(@PathVariable Long id) {
        careTakerService.deleteCaretaker(id);
        return "redirect:/caretakers";
    }

}
