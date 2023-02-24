// Обработка ошибок из application.properties
package com.itproger.shop.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")   // Обработка ошибок из application.properties
    public String error() {
        return "error";         // показываем наш шаблон с ошибкой
    }
}
