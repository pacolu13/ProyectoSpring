package com.proyecto.productListing.spec;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

import com.proyecto.productListing.entity.ProductListing;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProductListingSearch implements Specification<ProductListing>{
    private BigDecimal maxPrice;
    private BigDecimal minPrice;

    // Filtrar por ubicacion y fecha

    public ProductListingSearch(Optional<BigDecimal> maxPrice, Optional<BigDecimal> minPrice){
        this.maxPrice = maxPrice.orElse(null);
        this.minPrice = minPrice.orElse(null);

    }

    @Override
    public Predicate toPredicate(Root<ProductListing> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        
        if(maxPrice == null || maxPrice.isEmpty()){
            Expression<BigDecimal> maxPriceExp = criteriaBuilder(root.get("maxprice"));
            Predicate maxPricePredicate = criteriaBuilder.like("%",maxPriceExp,"%");
            predicates.add(maxPricePredicate);
        }
        if(minPrice == null || minPrice.isEmpty()){
            Expression<BigDecimal> minPriceExp = criteriaBuilder(root.get("minprice"));
            Predicate minPricePredicate = criteriaBuilder.like("%",minPriceExp,"%");
            predicates.add(minPricePredicate);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
