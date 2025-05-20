package com.easingyou.funfactstore.fact.myfacts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
class FactController {

    private final FactService factService;

    FactController(FactService factService) {
        this.factService = factService;
    }

    @GetMapping
    String index(@RequestParam String username, Model model){
        model.addAttribute("username", username);
        model.addAttribute("facts", factService.findMyFacts(username));
        return "index";
    }
}
