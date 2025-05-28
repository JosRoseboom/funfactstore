package com.easingyou.funfactstore.fact.myfacts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.easingyou.funfactstore.fact.Purchase;

interface MyPurchasesRepo extends JpaRepository<Purchase, Long> {

	List<Purchase> findByBuyer_Username(String username);

	@Query("""
				select new com.easingyou.funfactstore.fact.myfacts.FactDTO
					(purchase.purchaseDate, ff.fact, adm.email)
				from Purchase purchase
					join purchase.buyer buyer on buyer.username = :username
					join purchase.funFact ff
					join ff.admin adm
		""")
		List<FactDTO> findFactDTOsJPQL(String username);

	  @Query(value = """
	  	    select purch.purchase_date,ff1_0.fact,admin.email 
	  	    from purchase purch 
	  	        join app_user buyer on buyer.id=purch.buyer_id and buyer.username=:username 
	  	        join fun_fact ff1_0 on ff1_0.id=purch.fun_fact_id 
	  	        join app_user admin on admin.id=ff1_0.admin_id
	  	""", nativeQuery = true)
		List<FactDTONative> findFactDTOsNativeHibernate6(String username);

		record FactDTONative(java.sql.Timestamp purchaseDate, String text, String supportEmail) {}

		@Query(value = """
				select purch.purchase_date as purchaseDate,ff1_0.fact,admin.email as supportEmail 
				from purchase purch 
						join app_user buyer on buyer.id=purch.buyer_id and buyer.username=:username 
						join fun_fact ff1_0 on ff1_0.id=purch.fun_fact_id 
						join app_user admin on admin.id=ff1_0.admin_id
		""", nativeQuery = true)
		List<FactDTOProjection> findFactDTOsNativeBeforeHibernate6(String username);

		interface FactDTOProjection {
			java.sql.Timestamp getPurchaseDate();
			String getFact();
			String getSupportEmail();
		}

}
