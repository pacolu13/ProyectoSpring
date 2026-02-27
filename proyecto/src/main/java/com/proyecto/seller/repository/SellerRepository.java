package com.proyecto.seller.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.proyecto.seller.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, UUID>, JpaSpecificationExecutor<Seller> {

}
