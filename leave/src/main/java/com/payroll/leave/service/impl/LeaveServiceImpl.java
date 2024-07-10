package com.payroll.leave.service.impl;


import com.payroll.leave.dto.LeaveDto;
import com.payroll.leave.entity.Leave;
import com.payroll.leave.mapper.LeaveMapper;
import com.payroll.leave.repository.LeaveRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.payroll.leave.service.ILeaveService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
@Transactional
public class LeaveServiceImpl implements ILeaveService {

    private LeaveRepository leaveRepository;

    @Override
    public void createLeaveRequest(LeaveDto leaveDto) {
        Leave leave = LeaveMapper.mapToLeave(leaveDto, new Leave());
        leaveRepository.save(leave);
    }

    @Override
    public List<LeaveDto> getAllLeave(Long employeeId) {
        List<Leave> leaveList = leaveRepository.findAllByEmployeeId(employeeId);
        return leaveList.stream().map(leave
                -> LeaveMapper.mapToLeaveDto(leave, new LeaveDto())).toList();
    }


//    private CustomerRepository customerRepository;
//    private AccountsRepository accountsRepository;
//
//    @Override
//    public void createAccount(CustomerDto customerDto){
//
//
//        Optional<Customer> foundCustomer= customerRepository.findByMobileNumber(customerDto.getMobileNumber());
//        if(foundCustomer.isPresent()){
//            throw new CustomerAlreadyExistsException("Customer already exists for this mobile number");
//        }
//        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
////        customer.setCreatedAt(LocalDateTime.now());
////        customer.setCreatedBy("Me");
//        customerRepository.save(customer);
//
//        Accounts accounts = createNewAccount(customer.getCustomerId());
//        accountsRepository.save(accounts);
//    }
//
//    private Accounts createNewAccount(Long customerId){
//        Accounts accounts = new Accounts();
//        accounts.setCustomerId(customerId);
//        accounts.setAccountType("SAVINGS");
//        long randAcc = 100000000L + new Random().nextInt( 90000000);
//        accounts.setAccountNumber(String.valueOf(randAcc));
//        accounts.setBranchAddress("abc Home at post xyz colony");
////        accounts.setCreatedAt(LocalDateTime.now());
////        accounts.setCreatedBy("me");
//        return accounts;
//    }
//
//    @Override
//    public CustomerDto getAccountDetails(String mobileNumber){
//        Customer foundCustomer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->
//                new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
//        Accounts accounts = accountsRepository.findByCustomerId(foundCustomer.getCustomerId()).orElseThrow(()->
//                new ResourceNotFoundException("Accounts", "customerId", foundCustomer.getCustomerId().toString()));
//
//        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(foundCustomer, new CustomerDto());
//        AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts, new AccountsDto());
//
//        customerDto.setAccountsDto(accountsDto);
//
//        return customerDto;
//    }
//
//    @Override
//    public boolean updateAccount(String mobileNumber, CustomerDto customerDto){
//        boolean isUpdated = false;
//
//        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() ->
//                new ResourceNotFoundException("Customer",  "mobileNumber", mobileNumber));
//
//        if (customer != null) {
//
//            Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(()->
//                new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString()));
//
//            Customer updatedCustomer = CustomerMapper.mapToCustomer(customerDto, customer);
//            customerRepository.save(updatedCustomer);
//
//            Accounts updatedAccounts = AccountsMapper.mapToAccounts(customerDto.getAccountsDto(), accounts);
//            accountsRepository.save(updatedAccounts);
//
//            isUpdated = true;
//        }
//
//        return isUpdated;
//    }
//
//    @Override
//    public boolean deleteAccountMethod(String mobileNumber) {
//        boolean isDeleted = false;
//        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() ->
//                new ResourceNotFoundException("Customer",  "mobileNumber", mobileNumber));
//
//        if(customer != null){
//            accountsRepository.deleteByCustomerId(customer.getCustomerId());
//            customerRepository.deleteById(customer.getCustomerId());
//            isDeleted =true;
//        }
//        return isDeleted;
//    }
//
//    @Override
//    public List<CustomerDto> getAllAccount() {
//        List<Customer> customerList = customerRepository.findAll();
//        List<CustomerDto> customerDtoList = new ArrayList<>();
//
//        for(Customer customer : customerList){
//            CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
//            Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() ->
//                    new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString()) );
//
//            AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts, new AccountsDto());
//            customerDto.setAccountsDto(accountsDto);
//            customerDtoList.add(customerDto);
//        }
//
//        return customerDtoList;
//    }
//

}
