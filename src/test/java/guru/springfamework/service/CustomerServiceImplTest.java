package guru.springfamework.service;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper;

    CustomerService customerService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        customerMapper = CustomerMapper.INSTANCE;
        customerService = new CustomerServiceImpl(customerRepository, customerMapper);
    }

    @Test
    public void getAllCustomers() {

//        given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer());
        Mockito.when(customerRepository.findAll()).thenReturn(customers);

//        when
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();

//        then
        Assert.assertEquals(2, allCustomers.size());
    }

    @Test
    public void getCustomerByName() {

        final String FIRSTNAME = "Joe";
        final String LASTNAME = "Dalton";

        Customer customer = new Customer();
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        Mockito.when(customerRepository.findByName(Mockito.anyString())).thenReturn(customer);

        CustomerDTO customerDTO = customerService.getCustomerByName(FIRSTNAME);

        Assert.assertEquals(FIRSTNAME, customerDTO.getFirstname());
        Assert.assertEquals(LASTNAME, customer.getLastname());
    }
}