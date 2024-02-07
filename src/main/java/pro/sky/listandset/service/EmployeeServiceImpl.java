package pro.sky.listandset.service;

import org.springframework.stereotype.Service;
import pro.sky.listandset.employee.Employee;
import pro.sky.listandset.exeption.EmployeeAlreadyAddedException;
import pro.sky.listandset.exeption.EmployeeNotFoundException;
import pro.sky.listandset.exeption.EmployeeStorageIsFullException;
import pro.sky.listandset.exeption.IncorrectInputException;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static int maxEmployees = 10;
    private final Map<String, Employee> employees = new HashMap<>(Map.of(
            "FedorDvinyatin", new Employee("Fedor", "Dvinyatin", 12000, 1),
            "AlexandrGudkov", new Employee("Alexandr", "Gudkov", 15000, 1),
            "MaksimPotachev", new Employee("Maksim", "Potachev", 13000, 2),
            "AleksandrDruz", new Employee("Aleksandr", "Druz", 25000, 2)
    ));

    @Override
    public void addEmployee(String firstName, String lastName, int salary, int departmentId) {
        if (employees.size() >= maxEmployees) {
            throw new EmployeeStorageIsFullException("Employee storage is full");
        }

        Employee employee = new Employee(firstName, lastName, salary, departmentId);
        employees.put(makeKey(firstName, lastName), employee);

    }

    @Override
    public void removeEmployee(String firstName, String lastName) {
        var key = makeKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Employee is not found");
        }
        employees.remove(key);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        var key = makeKey(firstName, lastName);
        var employee = employees.get(key);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee is not found");
        }
        return employee;
    }

    private static String makeKey(String firstName, String lastName){
        if (containsOnlyAlphabet(firstName, lastName)) {
            return firstName + lastName;
        }
        throw new IncorrectInputException("Invalid characters in the query");
    }

    private static boolean containsOnlyAlphabet(String firstName, String lastName) {
        return isAlpha(firstName) & isAlpha(lastName);
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }
}