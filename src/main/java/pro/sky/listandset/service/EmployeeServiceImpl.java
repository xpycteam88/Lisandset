package pro.sky.listandset.service;

import org.springframework.stereotype.Service;
import pro.sky.listandset.employee.Employee;
import pro.sky.listandset.exeption.EmployeeAlreadyAddedException;
import pro.sky.listandset.exeption.EmployeeNotFoundException;
import pro.sky.listandset.exeption.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int maxEmployees = 3;
    private final List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Fedor", "Dvinyatin"),
            new Employee("Alexander", "Gudkov")
    ));

    @Override
    public void addEmployee(Employee employee) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        if (employees.size() >= maxEmployees) {
            throw new EmployeeStorageIsFullException("Employee storage is full");
        } else if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Employee is already added");
        } else {
            employees.add(employee);
        }
    }

    @Override
    public void removeEmployee(Employee employee) throws EmployeeNotFoundException {
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Employee is not found");
        } else {
            employees.remove(employee);
        }
    }

    @Override
    public void findEmployee(Employee employee) throws EmployeeNotFoundException {
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Employee is not found");
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }
}