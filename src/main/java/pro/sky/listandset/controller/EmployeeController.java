package pro.sky.listandset.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.listandset.employee.Employee;
import pro.sky.listandset.exeption.EmployeeStorageIsFullException;
import pro.sky.listandset.service.EmployeeService;

import java.util.List;

@RestController
public class EmployeeController {
    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/employee/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
            employeeService.addEmployee(employee);
            return employee;

    }

    @GetMapping(path = "/employee/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
        employeeService.removeEmployee(employee);
        return employee;
    }

    @GetMapping(path = "/employee/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
        employeeService.findEmployee(employee);
        return employee;
    }

    @GetMapping(path = "/employee/all")
    public List<Employee> getAllEmployees() {
       return employeeService.getAllEmployees();
    }
}
