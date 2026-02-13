package com.proyecto.vendedor.spec;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.proyecto.vendedor.entity.Vendedor;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class BusquedaVendedor implements Specification<Vendedor> {
    private String nombre;
    private String apellido;
    private String email;

    public BusquedaVendedor(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    @Override
    public Predicate toPredicate(Root<Vendedor> root, CriteriaQuery<?> query,
        CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();

            if(nombre != null && !nombre.isEmpty()) {
            Expression<String> nombreToLowerCase = criteriaBuilder.lower(root.get("nombre"));
            Predicate nombrePredicate = criteriaBuilder.like(nombreToLowerCase, "%" + nombre.toLowerCase() + "%");
            predicates.add(nombrePredicate);
        }

        if(apellido != null && !apellido.isEmpty()) {
            Expression<String> apellidoToLowerCase = criteriaBuilder.lower(root.get("apellido"));
            Predicate apellidoPredicate = criteriaBuilder.like(apellidoToLowerCase, "%" + apellido.toLowerCase() + "%");
            predicates.add(apellidoPredicate);
        }

        if(email != null && !email.isEmpty()) {
            Expression<String> emailToLowerCase = criteriaBuilder.lower(root.get("email"));
            Predicate emailPredicate = criteriaBuilder.like(emailToLowerCase, "%" + email.toLowerCase() + "%");
            predicates.add(emailPredicate);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }


}
