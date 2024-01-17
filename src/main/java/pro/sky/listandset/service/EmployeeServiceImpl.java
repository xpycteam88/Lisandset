package pro.sky.listandset.service;

import org.springframework.stereotype.Service;
import pro.sky.listandset.employee.Employee;
import pro.sky.listandset.exeption.EmployeeAlreadyAddedException;
import pro.sky.listandset.exeption.EmployeeNotFoundException;
import pro.sky.listandset.exeption.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static int maxEmployees = 3;
    private final Map<String, Employee> employees = new HashMap<>(Map.of(
            "FedorDvinyatin", new Employee("Fedor", "Dvinyatin"),
            "AlexanderGudkov", new Employee("Alexander", "Gudkov")
    ));

    @Override
    public void addEmployee(String firstName, String lastName) {
        if (employees.size() >= maxEmployees) {
            throw new EmployeeStorageIsFullException("Employee storage is full");
        }
        /* не стал использовать, так как добавится только уникальный объект
        if (employees.containsKey(firstName + lastName)) {
            throw new EmployeeAlreadyAddedException("Employee is already added");
        }

         */
        Employee employee = new Employee(firstName, lastName);
        employees.put(firstName + lastName, employee);

    }

    @Override
    public void removeEmployee(String firstName, String lastName) {
        var key = firstName + lastName;
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Employee is not found");
        }
        Employee employee = new Employee(firstName, lastName);
        employees.remove(key, employee);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        var key = firstName + lastName;
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Employee is not found");
        }
        //Employee employee = new Employee(firstName, lastName);
        return new Employee(firstName, lastName);
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }
}