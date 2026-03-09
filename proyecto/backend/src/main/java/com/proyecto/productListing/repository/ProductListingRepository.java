package com.proyecto.productListing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.productListing.entity.ProductListing;

@Repository
public interface ProductListingRepository extends JpaRepository<ProductListing, Long> {

    
}