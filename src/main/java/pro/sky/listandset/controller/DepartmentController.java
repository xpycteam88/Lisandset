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
    public Employee maxSalary(@RequestParam int departmentId) {
        return departmentService.findMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee minSalary(@RequestParam int departmentId) {
        return departmentService.findMinSalary(departmentId);
    }

    @GetMapping(value = "all", params = "departmentId")
    public List<Employee> employeesInDepartment(@RequestParam int departmentId) {
        return departmentService.getAllEmployeesDepartment(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> employeesInAllDepartments () {
        return departmentService.employeesInAllDepartments();
    }

}
