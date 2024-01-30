package pro.sky.listandset.service;

import org.springframework.stereotype.Service;
import pro.sky.listandset.employee.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public interface EmployeeService {
    void addEmployee(String firstName, String lastName, int salary, int department);
    void removeEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> getAllEmployees();
}
