package pro.sky.listandset.service;

import pro.sky.listandset.employee.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    public Employee findMaxSalaryInDepartment(int departmentId);

    public Employee findMinSalaryInDepartment(int departmentId);

    List<Employee> getAllEmployeesDepartment(int departmentId);

    Map<Integer, List<Employee>> groupEmployeesByDepartments();
}
