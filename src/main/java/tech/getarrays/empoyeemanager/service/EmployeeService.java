package tech.getarrays.empoyeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.empoyeemanager.exception.UserNotFoundException;
import tech.getarrays.empoyeemanager.model.Employee;
import tech.getarrays.empoyeemanager.repo.EmployeeRepo;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    //Create a repo property
    private final EmployeeRepo employeeRepo;
    //Constructor
    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return  employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee){return employeeRepo.save(employee);}

    public Employee findEmployeeById(Long id)
    {return employeeRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not fond"));}

    public void deleteEmployee(Long id){
        employeeRepo.deleteById(id);
    }

}
