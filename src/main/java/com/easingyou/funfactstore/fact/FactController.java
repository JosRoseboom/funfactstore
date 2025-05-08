package com.easingyou.funfactstore.fact;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
class FactController {

    private final FactService factService;

    FactController(FactService factService) {
        this.factService = factService;
    }

    @GetMapping("/{username}")
    String index(@PathVariable String username, Model model){
        model.addAttribute("facts", factService.findMyFacts(username));
        return "index";
    }
}
