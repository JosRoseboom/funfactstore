package com.easingyou.funfactstore.fact;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/facts")
class FactController {

    private final FactService factService;

    FactController(FactService factService) {
        this.factService = factService;
    }

    @GetMapping("/count")
    long getFactCount() {
        return factService.count();
    }
}
