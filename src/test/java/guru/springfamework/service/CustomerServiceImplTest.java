package guru.springfamework.service;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    private static final String FIRSTNAME = "Joe";
    private static final String LASTNAME = "Dalton";

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
        when(customerRepository.findAll()).thenReturn(customers);

//        when
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();

//        then
        assertEquals(2, allCustomers.size());
    }

    @Test
    public void getCustomerByName() {

        Customer customer = new Customer();
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        when(customerRepository.findByFirstname(Mockito.anyString())).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = customerService.getCustomerByName(FIRSTNAME);

        assertEquals(FIRSTNAME, customerDTO.getFirstname());
        assertEquals(LASTNAME, customer.getLastname());
    }

    @Test
    public void testSaveCustomer() {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(1l);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
    }

    @Test
    public void testDeleteCustomerById() {

        final Long id = 1L;

        customerRepository.deleteById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}