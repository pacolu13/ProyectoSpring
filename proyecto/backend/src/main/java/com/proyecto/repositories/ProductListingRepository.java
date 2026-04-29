package com.proyecto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.models.PostState;
import com.proyecto.models.ProductListing;
import com.proyecto.models.User;

@Repository
public interface ProductListingRepository extends JpaRepository<ProductListing, Long> {

    List<ProductListing> findByUser(User seller);

    List<ProductListing> findByPostState(PostState postState);

}