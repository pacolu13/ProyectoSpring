package com.proyecto.seller.entity;

import java.util.ArrayList;
import java.util.List;

import com.proyecto.productListing.entity.ProductListing;
import com.proyecto.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter             
@NoArgsConstructor  // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos

@Entity
@DiscriminatorValue("SELLER")
public class Seller extends User {

    private String cuit;

    @OneToMany(mappedBy="seller", cascade= CascadeType.ALL, orphanRemoval= true)
    private List<ProductListing> productsListingList = new ArrayList<>();

}
