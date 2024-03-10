package pro.sky.listandset.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.listandset.employee.Employee;
import pro.sky.listandset.exeption.EmployeeNotFoundException;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImpTest {

    @Mock
    EmployeeServiceImpl employeeService;

    @InjectMocks
    DepartmentServiceImp departmentService;

    @BeforeEach
    void setUp() {
        var employees = List.of(
                new Employee("Ivan", "Ivanov", 10_000, 1),
                new Employee("Petr", "Petrov", 12_000, 1),
                new Employee("Sergei", "Sergeev", 25_000, 2),
                new Employee("Aleksey", "Alekseev", 29_000, 2),
                new Employee("Dmitrii", "Dmitriev", 24_000, 2),
                new Employee("Viktor", "Viktorov", 31_000, 3),
                new Employee("Andrei", "Andreev", 37_000, 3),
                new Employee("Semen", "Semenov", 40_000, 4)
        );
        when(employeeService.getAllEmployees()).thenReturn(employees);
    }

    @Test
    void testSumSalaryInDepartment() {
        assertThat(departmentService.sumSalaryInDepartment(1)).isEqualTo(22_000);
        assertThat(departmentService.sumSalaryInDepartment(4)).isEqualTo(40_000);
    }

    @Test
    void testFindMaxSalaryInDepartment() {
        assertThat(departmentService.findMaxSalaryInDepartment(2))
                .isEqualTo(new Employee("Aleksey", "Alekseev", 29_000, 2));
        assertThat(departmentService.findMaxSalaryInDepartment(3))
                .isEqualTo(new Employee("Andrei", "Andreev", 37_000, 3));
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.findMaxSalaryInDepartment(13));
    }

    @Test
    void testFindMinSalaryInDepartment() {
        assertThat(departmentService.findMinSalaryInDepartment(2))
                .isEqualTo(new Employee("Dmitrii", "Dmitriev", 24_000, 2));
        assertThat(departmentService.findMinSalaryInDepartment(3))
                .isEqualTo(new Employee("Viktor", "Viktorov", 31_000, 3));
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.findMinSalaryInDepartment(13));
    }

    @Test
    void testGetAllEmployeesByDepartment() {
        var actual = departmentService.getAllEmployeesByDepartment(3);
        assertThat(actual).containsExactlyInAnyOrder(
                new Employee("Viktor", "Viktorov", 31_000, 3),
                new Employee("Andrei", "Andreev", 37_000, 3)
        );
    }

    @Test
    void testGroupEmployeesByDepartments() {
        var actual = departmentService.groupEmployeesByDepartments();
        var expected = Map.of(
                1, List.of(
                        new Employee("Ivan", "Ivanov", 10_000, 1),
                        new Employee("Petr", "Petrov", 12_000, 1)
                ),
                2, List.of(
                        new Employee("Sergei", "Sergeev", 25_000, 2),
                        new Employee("Aleksey", "Alekseev", 29_000, 2),
                        new Employee("Dmitrii", "Dmitriev", 24_000, 2)
                ),
                3, List.of(
                        new Employee("Viktor", "Viktorov", 31_000, 3),
                        new Employee("Andrei", "Andreev", 37_000, 3)
                ),
                4, List.of(
                        new Employee("Semen", "Semenov", 40_000, 4)
                )
        );
        assertThat(actual).isEqualTo(expected);
    }
}