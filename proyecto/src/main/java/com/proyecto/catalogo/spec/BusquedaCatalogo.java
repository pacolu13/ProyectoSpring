package com.proyecto.catalogo.spec;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.proyecto.catalogo.entity.Catalogo;
import com.proyecto.producto.entity.Producto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class BusquedaCatalogo implements Specification<Catalogo> {
    private String nombreProducto;
    private String categoriaProducto;
    private BigDecimal precioMinimo;
    private BigDecimal precioMaximo;

    public BusquedaCatalogo(String nombreProducto, String categoriaProducto, BigDecimal precioMinimo, BigDecimal precioMaximo) {
        this.nombreProducto = nombreProducto;
        this.categoriaProducto = categoriaProducto;
        this.precioMinimo = precioMinimo;
        this.precioMaximo = precioMaximo;
    }

    @Override
    public Predicate toPredicate(Root<Catalogo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        Join<Catalogo, Producto> productoJoin = root.join("producto");
        if(nombreProducto != null && !nombreProducto.isEmpty()) {
            Expression<String> nombreProductoToLowerCase = criteriaBuilder.lower(productoJoin.get("nombre"));
            Predicate nombreProductoPredicate = criteriaBuilder.like(nombreProductoToLowerCase, "%" + nombreProducto.toLowerCase() + "%");
            predicates.add(nombreProductoPredicate);
        }

        if(categoriaProducto != null && !categoriaProducto.isEmpty()) {
            Expression<String> categoriaProductoToLowerCase = criteriaBuilder.lower(productoJoin.get("categoria"));
            Predicate categoriaProductoPredicate = criteriaBuilder.like(categoriaProductoToLowerCase, "%" + categoriaProducto.toLowerCase() + "%");
            predicates.add(categoriaProductoPredicate);
        }

        if(precioMinimo != null && !precioMinimo.equals(BigDecimal.ZERO)) {
            Predicate precioMinimoPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("precio"), precioMinimo);
            predicates.add(precioMinimoPredicate);
        }

        if(precioMaximo != null && !precioMaximo.equals(BigDecimal.ZERO)) {
            Predicate precioMaximoPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("precio"), precioMaximo);
            predicates.add(precioMaximoPredicate);
        }

        // Y ordenamos por el precio de forma ascendente
        query.orderBy(criteriaBuilder.asc(root.get("precio")));

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    
    }

}
