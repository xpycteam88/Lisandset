package pro.sky.listandset.service;

import pro.sky.listandset.employee.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    public Employee findMaxSalary(int departmentId);

    public Employee findMinSalary(int departmentId);

    List<Employee> getAllEmployeesDepartment(int departmentId);

    Map<Integer, List<Employee>> employeesInAllDepartments();
}
