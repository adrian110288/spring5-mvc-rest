package guru.springfamework.controller;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends AbstractRestControllerTest {

    private static final String FIRSTNAME = "Joe";
    private static final String LASTNAME = "Doe";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testGetAllCustomers() throws Exception {

        List<CustomerDTO> customers = Arrays.asList(new CustomerDTO(), new CustomerDTO());

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void getCustomerByName() throws Exception {

        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        when(customerService.getCustomerByName(anyString())).thenReturn(customer);

        mockMvc.perform(get("/api/v1/customers/joe").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME)))
                .andExpect(jsonPath("$.lastname", equalTo(LASTNAME)));
    }

    @Test
    public void testCreateNewCustomer() throws Exception {

        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        CustomerDTO returnDto = new CustomerDTO();
        returnDto.setFirstname(customer.getFirstname());
        returnDto.setLastname(customer.getLastname());

        when(customerService.createNewCustomer(customer)).thenReturn(returnDto);

        mockMvc.perform(post("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME)))
                .andExpect(jsonPath("$.lastname", equalTo(LASTNAME)));

    }

    @Test
    public void testUpdateCustomer() throws Exception {

        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        CustomerDTO returnDto = new CustomerDTO();
        returnDto.setFirstname(customer.getFirstname());
        returnDto.setLastname(customer.getLastname());

        when(customerService.saveCustomerByDto(anyLong(), any(CustomerDTO.class))).thenReturn(returnDto);

        mockMvc.perform(put("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME)))
                .andExpect(jsonPath("$.lastname", equalTo(LASTNAME)));

    }
}