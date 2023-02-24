package com.itproger.shop.controllers;

import com.itproger.shop.models.Item;
import com.itproger.shop.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller         // Аннотация позволяет определить обычный класс, как контроллер
public class MainController {
    // Создаем объект на основе репозитория
    @Autowired
    private ItemRepository itemRepository;

    // отслеживание главной страницы. Можно записать @GetMapping("/")
    @GetMapping("/")
    public String index(Model model) {
        // Класс, в который добавляем все полученные записи
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "index";
    }

    @GetMapping("/about-us")
    public String about(@RequestParam(name = "userName",required = false, defaultValue = "World") String userName, Model model) {
        // Внутрь шаблона передаем свойство по ключу "name"
        model.addAttribute("name", userName);
        return "about";
    }
}
