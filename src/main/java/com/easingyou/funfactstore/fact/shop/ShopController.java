package com.easingyou.funfactstore.fact.shop;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shop")
class ShopController {
	private final Logger log = LoggerFactory.getLogger(ShopController.class);

  private final ShopService shopService;
	private final PurchaseService purchaseService;
	private final ExternalAPI externalAPI;

	ShopController(ShopService shopService, PurchaseService purchaseService, ExternalAPI externalAPI) {
		this.shopService = shopService;
		this.purchaseService = purchaseService;
		this.externalAPI = externalAPI;
	}

		@GetMapping
    String showShop(@RequestParam String username, Model model) {

			log.debug("Entering shopcontroller for user {}", username);

      model.addAttribute("username", username);
      model.addAttribute("lastPurchaseDate", shopService.getLastPurchaseDate(username).orElse(null));

			log.debug("Send email to the ceo to celebrate a potential customer!!!");
			externalAPI.sendEmailToCEO();
			log.debug("End of shopcontroller, entering shop for user {}", username);

      return "shop";
    }

    @PostMapping
    String processPayment(
            @RequestParam String username,
            @RequestParam String paymentMethod,
            Model model) {

				LocalDateTime purchaseDate = purchaseService.purchaseFunFact(username);

				model.addAttribute("username", username);
				model.addAttribute("lastPurchaseDate", purchaseDate);
        model.addAttribute("paymentProcessed", true);

        return "shop";
    }
}
