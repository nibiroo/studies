package io.github.nibiroo.domain.repository;

import io.github.nibiroo.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByName(String name);
    List<Customer> findByNameContains(String name);
    Boolean existsByName(String name);
    @Query(value = "select c from Customer c where c.id = :idParam")
    List<Customer> findSomethingIDJPQL(@Param("idParam") Long idParam);
    @Query(value = "select * from customer c where c.id = :idParam", nativeQuery = true)
    List<Customer> findSomethingIDSQL(@Param("idParam") Long idParam);
    @Query(value = "select * from customer c where c.name like :nameParam%", nativeQuery = true)
    List<Customer> findSomethingStartsNameSQL(@Param("nameParam") String nameParam);
    @Modifying
    @Query(value = "delete from customer c where c.name = :nameParam", nativeQuery = true)
    void deleteByName (String name);
    @Query(value = "select c from Customer c left join fetch c.purchaseOrders where c.id = :idParam")
    Customer findCustomerFetchOrders(@Param("idParam") Long idParam);
}