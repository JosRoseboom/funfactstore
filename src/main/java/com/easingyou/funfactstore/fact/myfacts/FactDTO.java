package com.easingyou.funfactstore.fact.myfacts;

import java.time.ZonedDateTime;

record FactDTO(ZonedDateTime purchaseDate, String text, String supportEmail) {
}
