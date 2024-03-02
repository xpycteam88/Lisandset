package pro.sky.listandset.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.listandset.employee.Employee;
import pro.sky.listandset.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{id}/salary/sum")
    public Integer sumSalaryAllEmployeesByDepartment(@PathVariable int id) {
        return departmentService.sumSalaryInDepartment(id);
    }
    @GetMapping("{id}/salary/max")
    public Employee findEmployeeWithMaxSalaryByDepartment(@PathVariable int id) {
        return departmentService.findMaxSalaryInDepartment(id);
    }

    @GetMapping("{id}/salary/min")
    public Employee findEmployeeWithMinSalaryByDepartment(@PathVariable int id) {
        return departmentService.findMinSalaryInDepartment(id);
    }

    @GetMapping("{id}/employees")
    public List<Employee> employeesInDepartment(@RequestParam int id) {
        return departmentService.getAllEmployeesByDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> groupEmployeesByDepartments () {
        return departmentService.groupEmployeesByDepartments();
    }

}
