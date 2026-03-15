package com.proyecto.user.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.proyecto.auth.entity.Token;
import com.proyecto.cart.entity.Cart;
import com.proyecto.order.entity.Order;
import com.proyecto.productListing.entity.ProductListing;
import com.proyecto.rol.entity.Rol;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@Builder

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
@Table(name = "usuarios")
@NoArgsConstructor // ← JPA necesita este
@AllArgsConstructor // ← Builder necesita este
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // UUID es un identificador único universal mas seguro.
    private String username;
    private String phoneNumber;
    private String email;
    private String password;
    private String cuit;
    private String location;

    @Builder.Default
    private Integer totalSales = 0;
    @Builder.Default
    private Float rating = 5f;
    @Builder.Default
    private Boolean sellerEnable = false;
    @Builder.Default
    private BigDecimal balance = BigDecimal.valueOf(500);
    @Builder.Default
    private Boolean active = true;
    @Builder.Default
    private LocalDateTime creationDate = LocalDateTime.now();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL) // Crea automaticamente el Carrito
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Order> ordersList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Token> tokens;

    @ManyToMany
    @JoinTable(name = "user_rol", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private List<Rol> rolesList;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductListing> productsListingList = new ArrayList<>();

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }
}
