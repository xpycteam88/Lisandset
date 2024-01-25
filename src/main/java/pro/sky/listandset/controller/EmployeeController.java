package pro.sky.listandset.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.listandset.employee.Employee;
import pro.sky.listandset.service.EmployeeService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(path = "/add")
    public void add(@RequestParam String firstName,
                                @RequestParam String lastName) {
            employeeService.addEmployee(firstName, lastName);

    }

    @GetMapping(path = "/remove")
    public void removeEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName) {
        employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping(path = "/all")
    public Collection<Employee> getAllEmployees() {
       return employeeService.getAllEmployees();
    }
}
