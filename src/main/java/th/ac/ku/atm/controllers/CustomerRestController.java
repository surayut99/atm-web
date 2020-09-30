package th.ac.ku.atm.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.ac.ku.atm.models.Customer;
import th.ac.ku.atm.services.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {
  private CustomerService customerService;

  public CustomerRestController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public List<Customer> getAll() {
    return customerService.getCustomer();
  }

  @GetMapping("/{id}")
  public Customer getOne(@PathVariable int id) {
    return customerService.findCustomer(id);
  }

}
