package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void testMapCustomerToCustomerDto() {

        final String firstname = "Joe";
        final String lastname = "Dalton";

        Customer customer = new Customer();
        customer.setFirstname(firstname);
        customer.setLastname(lastname);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(firstname, customerDTO.getFirstname());
        assertEquals(lastname, customerDTO.getLastname());

    }
}