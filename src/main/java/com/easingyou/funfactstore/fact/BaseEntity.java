package com.easingyou.funfactstore.fact;

import org.hibernate.proxy.HibernateProxy;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
public class BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	public Long getId() {
		return id;
	}

	// Equals() and hashCode() for entities should be consistent across all state transitions.
	 //
	 // If an identifier is known before persisting (natural id like social security number or id that is known upfront), that identifier could be used as a base for equals and hashcode.
	 //
	 // If no such identifier is known (the database generates it on insert), the implementation shown here could be used.
	 //
	 // Very good posts on this:
	 // - https://jpa-buddy.com/blog/hopefully-the-final-article-about-equals-and-hashcode-for-jpa-entities-with-db-generated-ids/
	 // - https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
	@Override
	public final boolean equals(Object o) {
		if (this == o)
			return true;

		return o instanceof BaseEntity baseEntity
				&& this.getId() != null
				&& this.getId().equals(baseEntity.getId())
				&& this.getEffectiveClass() == baseEntity.getEffectiveClass();
	}

	@Override
	public final int hashCode() {
		return getEffectiveClass().hashCode();
	}

	private Class<?> getEffectiveClass() {
		return this instanceof HibernateProxy hibernateProxy ? hibernateProxy.getHibernateLazyInitializer().getPersistentClass() : getClass();
	}

	// when final, we can call toString() on a proxied entity without instantiating it
	//
 	// when final is dropped, it will (try to) instantiate a proxy when called on a proxy
	public final String toString() {
		return getEffectiveClass().getSimpleName() + "[id=" + getId() + "]";
	}
}
