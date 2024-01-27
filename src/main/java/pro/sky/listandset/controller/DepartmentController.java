package pro.sky.listandset.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.listandset.employee.Employee;
import pro.sky.listandset.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee findEmployeeWithMaxSalary(@RequestParam int departmentId) {
        return departmentService.findMaxSalaryInDepartment(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee findEmployeeWithMinSalary(@RequestParam int departmentId) {
        return departmentService.findMinSalaryInDepartment(departmentId);
    }

    @GetMapping(value = "all", params = {"departmentId"})
    public List<Employee> employeesInDepartment(@RequestParam int departmentId) {
        return departmentService.getAllEmployeesDepartment(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> groupEmployeesByDepartments () {
        return departmentService.groupEmployeesByDepartments();
    }

}
