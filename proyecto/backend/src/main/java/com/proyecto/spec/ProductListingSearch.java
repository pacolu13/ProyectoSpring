package com.proyecto.spec;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

import com.proyecto.models.ProductListing;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProductListingSearch implements Specification<ProductListing> {
    private BigDecimal maxPrice;
    private BigDecimal minPrice;

    // Filtrar por ubicacion y fecha

    public ProductListingSearch(Optional<BigDecimal> maxPrice, Optional<BigDecimal> minPrice) {
        this.maxPrice = maxPrice.orElse(null);
        this.minPrice = minPrice.orElse(null);

    }

    @Override
    @SuppressWarnings("null")
    public Predicate toPredicate(Root<ProductListing> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (maxPrice == null || maxPrice.equals(BigDecimal.ZERO)) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
        }
        if (minPrice == null || minPrice.equals(BigDecimal.ZERO)) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
