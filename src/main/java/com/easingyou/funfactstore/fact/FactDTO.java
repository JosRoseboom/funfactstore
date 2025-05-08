package com.easingyou.funfactstore.fact;

import java.time.ZonedDateTime;

record FactDTO(ZonedDateTime purchaseDate, String text, String supportEmail) {
}
