package com.proyecto.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

import com.proyecto.models.User;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@SuppressWarnings("null")
public class SellerSearch implements Specification<User> {
    private String name;
    private String lastname;
    private String email;

    public SellerSearch(Optional<String> name2, Optional<String> lastname2, Optional<String> email2) {
        this.name = name2.orElse(null);
        this.lastname = lastname2.orElse(null);
        this.email = email2.orElse(null);
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            Expression<String> nameToLowerCase = criteriaBuilder.lower(root.get("name"));
            Predicate namePredicate = criteriaBuilder.like(nameToLowerCase, "%" + name.toLowerCase() + "%");
            predicates.add(namePredicate);
        }

        if (lastname != null && !lastname.isEmpty()) {
            Expression<String> lastnameToLowerCase = criteriaBuilder.lower(root.get("lastname"));
            Predicate lastnamePredicate = criteriaBuilder.like(lastnameToLowerCase, "%" + lastname.toLowerCase() + "%");
            predicates.add(lastnamePredicate);
        }

        if (email != null && !email.isEmpty()) {
            Expression<String> emailToLowerCase = criteriaBuilder.lower(root.get("email"));
            Predicate emailPredicate = criteriaBuilder.like(emailToLowerCase, "%" + email.toLowerCase() + "%");
            predicates.add(emailPredicate);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
