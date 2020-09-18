package th.ac.ku.atm.services;

import org.springframework.stereotype.Service;
import th.ac.ku.atm.basedClasses.Customer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private List<Customer> customerList;

    @PostConstruct
    public void postConstruct() {
        this.customerList = new ArrayList<>();
    }

    public void createCustomer(Customer customer) {
        customerList.add(customer);
    }

    public List<Customer> getCustomer() {
        return new ArrayList<>(this.customerList);
    }
}
