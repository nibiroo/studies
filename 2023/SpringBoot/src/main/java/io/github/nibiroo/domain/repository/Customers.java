package io.github.nibiroo.domain.repository;

import io.github.nibiroo.domain.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Customers {

    private static final String INSERT = "INSERT INTO CUSTOMER(NAME) VALUES (?)";
    private static final String SELECT_ALL = "SELECT * FROM CUSTOMER";
    private static final String UPDATE = "UPDATE CUSTOMER SET NAME = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM CUSTOMER WHERE ID = ?";

    // JdbcTemplate -> Will get information about database connection
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Customer save (Customer customer){
        jdbcTemplate.update(INSERT, customer.getNome());
        return customer;
    }

    public Customer update (Customer customer){
        jdbcTemplate.update(UPDATE, customer.getNome(), customer.getId());
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
        return new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer idCustomer = rs.getInt("id");
                String nameCustomer = rs.getString("name");
                return new Customer(idCustomer, nameCustomer);
            }
        };
    }
}
