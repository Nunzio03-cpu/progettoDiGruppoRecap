package co.develhope.progettoDiGruppoRecap.Controller;

import co.develhope.progettoDiGruppoRecap.Entity.DepartmentEntity;
import co.develhope.progettoDiGruppoRecap.Entity.EmployeeEntity;
import co.develhope.progettoDiGruppoRecap.Service.DepartmentService;
import co.develhope.progettoDiGruppoRecap.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    //Create an employee
    @PostMapping("/create")
    public ResponseEntity<EmployeeEntity> createEmployee(@RequestBody EmployeeEntity employee) {
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    //find all employee
    @GetMapping("/findAll")
    public ResponseEntity<List<EmployeeEntity>> findAll() {
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }

    //delete employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmployeeEntity> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Optional<EmployeeEntity>> updateEmployee(@PathVariable Long id, @RequestBody EmployeeEntity employee) {
        Optional<EmployeeEntity> employeeEntityOptional = employeeService.updateEmployee(id, employee);
        if (employeeEntityOptional.isPresent()) {
            return ResponseEntity.ok(employeeEntityOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("find-by-id")
    public Optional<EmployeeEntity> findById(@PathVariable Long id){
        return employeeService.findById(id);
    }

    @PutMapping("/add-department-to-employee")
    public ResponseEntity<Optional<EmployeeEntity>> addDepartmentToEmployee(@RequestParam Long idDepartment,
                                                                              @RequestParam Long idEmployee){
        Optional<EmployeeEntity> employeeEntityOptional = employeeService.addDepartmentToEmployee(idDepartment,
                idEmployee);
        if (employeeEntityOptional.isPresent()) {
            return ResponseEntity.ok(employeeEntityOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/remove-department-from-employee")
    public ResponseEntity<Optional<EmployeeEntity>> removeDeparrtmentFromEmployee(@RequestParam Long idDepartment,
                                                                                 @RequestParam Long idEmployee){
        Optional<EmployeeEntity> employeeEntityOptional = employeeService.removeDepartmentFromEmployee(idDepartment,
                idEmployee);
        if (employeeEntityOptional.isPresent()) {
            return ResponseEntity.ok(employeeEntityOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count-employees-for-department")
    public ResponseEntity<Long> countNumberOfEmployeesForDepartment(@RequestParam Long idDepartment){
        return ResponseEntity.ok(employeeService.countNumberOfEmployeesForDepartment(idDepartment));
    }
}
