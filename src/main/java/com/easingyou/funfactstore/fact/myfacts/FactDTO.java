package com.easingyou.funfactstore.fact.myfacts;

import java.time.LocalDateTime;

record FactDTO(LocalDateTime purchaseDate, String text, String supportEmail) {

}
