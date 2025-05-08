package com.easingyou.funfactstore.fact.shop;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.easingyou.funfactstore.fact.AppUser;
import com.easingyou.funfactstore.fact.AppUserRepo;
import com.easingyou.funfactstore.fact.Purchase;

@Controller
@RequestMapping("/shop")
class ShopController {

  private final ShopService shopService;

	ShopController(ShopService shopService) {
		this.shopService = shopService;
	}

	@GetMapping("/{username}")
    String showShop(@PathVariable String username, Model model) {

        model.addAttribute("username", username);
        model.addAttribute("lastPurchaseDate", shopService.getLastPurchaseDate(username).orElse(null));
        
        return "shop";
    }

    @PostMapping
    String processPayment(
            @RequestParam String username,
            @RequestParam String paymentMethod,
            Model model) {
        
        // Here you would process the payment and create a new purchase
        // For now, we'll just acknowledge the payment method
        model.addAttribute("username", username);
        model.addAttribute("paymentMethod", paymentMethod);
        model.addAttribute("paymentProcessed", true);
        
        return "shop";
    }
}
