package io.github.nibiroo.domain.repository;

import io.github.nibiroo.domain.entity.Customer;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomersRepository {

    private static final String SELECT_ALL = "SELECT * FROM CUSTOMER ";
    private static final String UPDATE = "UPDATE CUSTOMER SET name = ? WHERE id = ? ";
    private static final String DELETE = "DELETE FROM CUSTOMER WHERE id = ? ";

    // JdbcTemplate -> Will get information about database connection
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // EntityManager -> Responsible for performing any entity operation in the database
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Customer save (Customer customer){
        entityManager.persist(customer);
        return customer;
    }

    public Customer update (Customer customer){
        jdbcTemplate.update(UPDATE, customer.getName(), customer.getId());
        return customer;
    }

    public void delete (Customer customer){
        delete(customer.getId());
    }
    public void delete (Integer id){
        jdbcTemplate.update(DELETE, id);
    }

    public List<Customer> getCustomer(String name){
        return jdbcTemplate.query(
                SELECT_ALL.concat(" where name like ?"),
                new Object[]{"%"+name+"%"},
                getCustomerRowMapper()
        );
    }

    public List<Customer> getAllCustomers(){
        // How will we use more, create a method for getCustomerMapper with CTRL+ALT+M when code selected
        return jdbcTemplate.query(SELECT_ALL, getCustomerRowMapper());
    }

    // RowMapper<Customer> -> For each row of result, bring the information and return a new Customer(for each data row)
    private static RowMapper<Customer> getCustomerRowMapper() {
        return (rs, rowNum) -> {
            Integer idCustomer = rs.getInt("id");
            String nameCustomer = rs.getString("name");
            return new Customer(idCustomer, nameCustomer);
        };
    }
}
