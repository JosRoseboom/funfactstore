package com.easingyou.funfactstore.fact;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FunFact extends BaseEntity {
	private static final List<String> BAD_WORDS = List.of("boring", "stupid");

	@Column(nullable = false)
	private String fact;

	private String explanation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private AppUser admin;

	public void sanitizeAdminBased() {

	  List<String> adminBadWords = Map.of(
	      "com", List.of("dumb", "boring"),
	      "nl", List.of("stupid")
	  ).getOrDefault(admin.getTopLevelDomain(), BAD_WORDS);

	  sanitize(adminBadWords);

	}

	public void sanitize() {
		sanitize(BAD_WORDS);
	}

	private void sanitize(List<String> badWords) {
		fact = sanitize(fact, badWords);
		explanation = sanitize(explanation, badWords);
	}

	private String sanitize(String in, List<String> badWords) {
		if (in == null) {
			return null;
		}
		String result = in;

		// Replace bad words with **** (case insensitive, word-boundary)
		for (String badWord : badWords) {
			result = result.replaceAll("(?i)\\b" + Pattern.quote(badWord) + "\\b", "****");
		}

		if (!result.endsWith(".")) {
			result = result + ".";
		}

		return result;
	}

	public String getFact() {
		return fact;
	}

	public AppUser getAdmin() {
		return admin;
	}
}
