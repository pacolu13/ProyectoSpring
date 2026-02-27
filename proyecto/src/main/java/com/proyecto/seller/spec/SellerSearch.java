package com.proyecto.seller.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

import com.proyecto.seller.entity.Seller;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@SuppressWarnings("null")
public class SellerSearch implements Specification<Seller> {
    private String nombre;
    private String apellido;
    private String email;

    public SellerSearch(Optional<String> nombre2, Optional<String> apellido2, Optional<String> email2) {
        this.nombre = nombre2.orElse(null);
        this.apellido = apellido2.orElse(null);
        this.email = email2.orElse(null);
    }

    @Override
    public Predicate toPredicate(Root<Seller> root, CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (nombre != null && !nombre.isEmpty()) {
            Expression<String> nombreToLowerCase = criteriaBuilder.lower(root.get("nombre"));
            Predicate nombrePredicate = criteriaBuilder.like(nombreToLowerCase, "%" + nombre.toLowerCase() + "%");
            predicates.add(nombrePredicate);
        }

        if (apellido != null && !apellido.isEmpty()) {
            Expression<String> apellidoToLowerCase = criteriaBuilder.lower(root.get("apellido"));
            Predicate apellidoPredicate = criteriaBuilder.like(apellidoToLowerCase, "%" + apellido.toLowerCase() + "%");
            predicates.add(apellidoPredicate);
        }

        if (email != null && !email.isEmpty()) {
            Expression<String> emailToLowerCase = criteriaBuilder.lower(root.get("email"));
            Predicate emailPredicate = criteriaBuilder.like(emailToLowerCase, "%" + email.toLowerCase() + "%");
            predicates.add(emailPredicate);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
