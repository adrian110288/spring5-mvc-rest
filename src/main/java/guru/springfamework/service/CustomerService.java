package guru.springfamework.service;

import guru.springfamework.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerByName(String name);

    CustomerDTO createNewCustomer(CustomerDTO customerDto);

    CustomerDTO saveCustomerByDto(Long id, CustomerDTO customer);

    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomerById(Long id);
}
