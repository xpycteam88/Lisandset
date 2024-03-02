package pro.sky.listandset.service;

import org.junit.jupiter.api.Test;
import pro.sky.listandset.employee.Employee;
import pro.sky.listandset.exeption.EmployeeNotFoundException;
import pro.sky.listandset.exeption.EmployeeStorageIsFullException;
import pro.sky.listandset.exeption.IncorrectInputException;

import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @Test
    void testAddEmployee() {
        employeeService.addEmployee("semen", "semenov", 33_000, 3);
        var actual = employeeService.findEmployee("Semen", "Semenov");
        var expected = new Employee("semen", "semenov", 33_000, 3);
        assertThat(actual).isNotNull();
        assertEquals("Semen", actual.getFirstName());
        assertEquals("Semenov", actual.getLastName());
        assertEquals(33_000, actual.getSalary());
        assertEquals(3, actual.getDepartmentId());
        assertEquals(expected, actual);

    }

    @Test
    void testFindEmployee() {
        employeeService.addEmployee("Semen", "Semenov", 33_000, 3);
        var actual = employeeService.findEmployee("Semen", "Semenov");
        assertThat(actual).isEqualTo(new Employee("Semen", "Semenov", 33_000, 3));
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee("NotFirstName", "NotLastName"));
    }

    @Test
    void testRemoveEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee("Ermak", "Ermakov"));
        employeeService.addEmployee("Alexandr", "Gudkov", 15_000, 1);
        employeeService.addEmployee("Maksim", "Potachev", 23_000, 2);
        var actual = employeeService.findEmployee("Maksim", "Potachev");
        assertThat(actual).isNotNull();
        employeeService.removeEmployee("Maksim", "Potachev");
        assertThat(employeeService.getAllEmployees()).containsExactlyInAnyOrder(
                new Employee("Alexandr", "Gudkov", 15_000, 1)
        );
    }

    @Test
    void testAddMaxEmployees() {
        employeeService.addEmployee("Fedor", "Dvinyatin", 12_000, 1);
        employeeService.addEmployee("Alexandr", "Gudkov", 15_000, 1);
        employeeService.addEmployee("Maksim", "Potachev", 23_000, 2);
        employeeService.addEmployee("Aleksandr", "Druz", 25_000, 2);
        assertThrows(EmployeeStorageIsFullException.class,
                () -> employeeService.addEmployee("Semen", "Semenov", 33_000, 3));

    }

    @Test
    void testNameIsOnlyAlphabet() {
        assertThrows(IncorrectInputException.class,
                () -> employeeService
                        .addEmployee("1Semen", "Semenov", 33_000, 3));
        assertThrows(IncorrectInputException.class,
                () -> employeeService
                        .addEmployee("Semen1", "Semenov", 33_000, 3));
        assertThrows(IncorrectInputException.class,
                () -> employeeService
                        .addEmployee("Semen", "Semen1ov", 33_000, 3));
    }

    @Test
    void testGetAllEmployees() {
        employeeService.addEmployee("Alexandr", "Gudkov", 15_000, 1);
        employeeService.addEmployee("Maksim", "Potachev", 23_000, 2);

        var actual = employeeService.getAllEmployees();
        assertThat(actual).containsExactlyInAnyOrder(
                new Employee("Alexandr", "Gudkov", 15_000, 1),
                new Employee("Maksim", "Potachev", 23_000, 2)
        );
    }

}