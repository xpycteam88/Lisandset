package pro.sky.listandset.service;

import org.springframework.stereotype.Service;
import pro.sky.listandset.employee.Employee;

import java.util.List;

@Service
public interface EmployeeService {
    void addEmployee(Employee employee);
    void removeEmployee(Employee employee);
    void findEmployee(Employee employee);

    List<Employee> getAllEmployees();
}
