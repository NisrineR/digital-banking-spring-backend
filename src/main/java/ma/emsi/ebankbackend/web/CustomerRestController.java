package ma.emsi.ebankbackend.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.ebankbackend.dtos.BankAccountDTO;
import ma.emsi.ebankbackend.dtos.CustomerDTO;
import ma.emsi.ebankbackend.entities.Customer;
import ma.emsi.ebankbackend.exceptions.CustomerNotFoundExeption;
import ma.emsi.ebankbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {
    private BankAccountService bankAccountService;
    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return bankAccountService.listCustemers();
        //1h42 in video 2
    }

    @GetMapping("/customers/{id}")//path param norm rest
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundExeption {
        return bankAccountService.getCustomer(customerId);
    }
    @PostMapping("/customers")//@RequestBody on recupaire de core de la reques json
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return bankAccountService.saveCustomer(customerDTO);

    }
    @GetMapping("/customers/search")//@RequestBody on recupaire de core de la reques json
    public List<CustomerDTO>serchCustomer(@RequestParam(name = "keyword",defaultValue = "")
                                                      String keyword){
        return bankAccountService.serachCustomer("%"+keyword+"%");

    }
    @PutMapping("/customers/{customerId}")
    public CustomerDTO upadteCustomer(@PathVariable Long customerId ,@RequestBody CustomerDTO customerDTO){
       customerDTO.setId(customerId);
       return bankAccountService.updateCustomer(customerDTO);
    }
    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        bankAccountService.deleteCustomer(id);
    }
    @GetMapping("/customers/{custemerId}/accounts")
    public List<BankAccountDTO> getCustemerAccounts(@PathVariable Long custemerId){
        return bankAccountService.getCustemerAccounts(custemerId);
    }



}
