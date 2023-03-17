package io.github.nibiroo.domain.repository;

import io.github.nibiroo.domain.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomersRepository {

    // EntityManager -> Responsible for performing any entity operation in the database
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Customer save (Customer customer){
        entityManager.persist(customer);
        return customer;
    }

    @Transactional
    public Customer update (Customer customer){
        entityManager.merge(customer);
        return customer;
    }

    @Transactional
    public void delete (Customer customer){
        if(!entityManager.contains(customer)){
            customer = entityManager.merge(customer);
        }
        entityManager.remove(customer);
    }

    @Transactional
    public void delete (Integer id){
        Customer customer = entityManager.find(Customer.class, id);
        delete(customer);
    }

    @Transactional(readOnly = true)
    public List<Customer> getCustomer(String name){
        String jpql = "select c from Customer c where c.name like :name ";
        TypedQuery<Customer> query = entityManager.createQuery(jpql, Customer.class);
        query.setParameter("name", "%"+name+"%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers(){
        return entityManager
                .createQuery("from Customer", Customer.class)
                .getResultList();
    }
}
