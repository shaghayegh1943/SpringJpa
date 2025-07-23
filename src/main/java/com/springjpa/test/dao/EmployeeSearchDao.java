package com.springjpa.test.dao;

import com.springjpa.test.model.employee.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeSearchDao {
    //here i injected entity manager to access persistenceContext
    private final EntityManager em;

    public List<Employee> findAllBySimpleQuery(String firstName, String lastName, String email) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
        //select * from Employess
        Root<Employee> root = query.from(Employee.class);
        //prepare where clause
        Predicate predicateFirstName = criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%");
        Predicate predicateLastName = criteriaBuilder.like(root.get("lastName"), "%" + firstName + "%");
        Predicate predicateEmail = criteriaBuilder.like(root.get("email"), "%" + firstName + "%");

        Predicate orPredicate = criteriaBuilder.or(predicateFirstName, predicateLastName, predicateEmail);
        //final query with where clause would be
        query.where(orPredicate);
        TypedQuery<Employee> typedQuery = em.createQuery(query);

        return typedQuery.getResultList();
    }

    public List<Employee> findAllByCriteria(SearchRequest request)
    {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
        List<Predicate> predicates = new ArrayList<>();
        //select from amployee
        Root<Employee> root = query.from(Employee.class);
        if(request.getFirstName() != null){
            Predicate predicateFirstName = criteriaBuilder.like(root.get("firstName"), "%" + request.getFirstName() + "%");
            predicates.add(predicateFirstName);
        }
        if(request.getLastName() != null){
            Predicate predicateLastName = criteriaBuilder.like(root.get("lastName"), "%" + request.getLastName() + "%");
            predicates.add(predicateLastName);
        }
        if(request.getEmail() != null){
            Predicate predicateEmail = criteriaBuilder.like(root.get("email"), "%" + request.getEmail() + "%");
            predicates.add(predicateEmail);
        }
        //it will return a table of predicates
        query.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
        TypedQuery<Employee> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();

    }




}
