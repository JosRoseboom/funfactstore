package com.easingyou.funfactstore.fact.shop;

import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shop")
class ShopController {
	private final Logger log = LoggerFactory.getLogger(ShopController.class);

  private final ShopService shopService;
	private final PurchaseService purchaseService;

	ShopController(ShopService shopService, PurchaseService purchaseService) {
		this.shopService = shopService;
		this.purchaseService = purchaseService;
	}

		@GetMapping("/{username}")
    String showShop(@PathVariable String username, Model model) {

			log.info("Entering shopcontroller for user {}", username);

      model.addAttribute("username", username);
      model.addAttribute("lastPurchaseDate", shopService.getLastPurchaseDate(username).orElse(null));


			log.info("End of shopcontroller, entering shop for user {}", username);

      return "shop";
    }

    @PostMapping("/{username}")
    String processPayment(
            @PathVariable String username,
            @RequestParam String paymentMethod,
            Model model) {

				ZonedDateTime purchaseDate = purchaseService.purchaseFunFact(username);

				model.addAttribute("username", username);
				model.addAttribute("lastPurchaseDate", purchaseDate);
        model.addAttribute("paymentProcessed", true);

        return "shop";
    }
}
