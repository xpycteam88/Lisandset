package pro.sky.listandset.service;

import pro.sky.listandset.employee.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Integer sumSalaryInDepartment(int departmentId);
    Employee findMaxSalaryInDepartment(int departmentId);

    Employee findMinSalaryInDepartment(int departmentId);

    List<Employee> getAllEmployeesByDepartment(int departmentId);

    Map<Integer, List<Employee>> groupEmployeesByDepartments();
}
