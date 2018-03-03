package guru.springfamework.controller;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public ResponseEntity<CustomerListDTO> getAllCustomers() {

        return new ResponseEntity<>(
                new CustomerListDTO(customerService.getAllCustomers()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{name}")
    public ResponseEntity<CustomerDTO> getCustomerByName(@PathVariable String name) {
        return new ResponseEntity<>(
                customerService.getCustomerByName(name),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customer) {

        CustomerDTO newCustomer = customerService.createNewCustomer(customer);

        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String id, @RequestBody CustomerDTO customer) {

        CustomerDTO updatedCustomer = customerService.saveCustomerByDto(Long.valueOf(id), customer);

        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);

    }
}
