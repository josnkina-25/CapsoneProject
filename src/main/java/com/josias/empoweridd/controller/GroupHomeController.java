package com.josias.empoweridd.controller;

import com.josias.empoweridd.model.GroupHome;
import com.josias.empoweridd.service.GroupHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/homes")
public class GroupHomeController {
    @Autowired
    private GroupHomeService groupHomeService;
    @GetMapping
    public String listHomes(Model model) {
        model.addAttribute("homes", groupHomeService.getAllGroupHomes());
        return "home/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("home", new GroupHome());
        return "home/add";
    }

    @PostMapping("/add")
    public String addHome(@ModelAttribute GroupHome home) {
        groupHomeService.createGroupHome(home);
        return "redirect:/homes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
      GroupHome groupHome = groupHomeService.getGroupHomeById(id).orElse(new GroupHome());
      model.addAttribute("home", groupHome);
        return "home/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateHome(@PathVariable Long id, @ModelAttribute GroupHome home) {
        groupHomeService.updateGroupHome(home, id);
        return "redirect:/homes";
    }
    @GetMapping("/delete/{id}")
    public String deleteHome(@PathVariable Long id) {
        groupHomeService.deleteGroupHome(id);
        return "redirect:/homes";
    }
}
