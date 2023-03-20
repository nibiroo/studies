package io.github.nibiroo.domain.repository;

import io.github.nibiroo.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomersRepository extends JpaRepository<Customer, Integer> {

    Customer findByName(String name);

    List<Customer> findByNameContains(String name);

    Boolean existsByName(String name);

    @Query(value = "select c from Customer c where c.id = :idParam")
    List<Customer> findSomethingIDJPQL(@Param("idParam") Integer idParam);

    @Query(value = "select * from customer c where c.id = :idParam", nativeQuery = true)
    List<Customer> findSomethingIDSQL(@Param("idParam") Integer idParam);

    @Query(value = "select * from customer c where c.name like :nameParam%", nativeQuery = true)
    List<Customer> findSomethingStartsNameSQL(@Param("nameParam") String nameParam);

    @Modifying
    @Query(value = "delete from customer c where c.name = :nameParam", nativeQuery = true)
    void deleteByName (String name);

    @Query(value = "select c.*, inv.date_order, inv.total from customer as c left join invoice as inv on c.id = inv.id_customer where c.id = :idParam ", nativeQuery = true)
    Customer findCustomerFetchInvoices(@Param("idParam") Integer idParam);
}