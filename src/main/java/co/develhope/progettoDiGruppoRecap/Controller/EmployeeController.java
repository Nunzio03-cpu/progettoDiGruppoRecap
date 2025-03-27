package co.develhope.progettoDiGruppoRecap.Controller;

import co.develhope.progettoDiGruppoRecap.Entity.EmployeeEntity;
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
    EmployeeService employeeService;

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
    public ResponseEntity<Optional<EmployeeEntity>> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeEntity employee) {
        Optional<EmployeeEntity> employeeEntityOptional = employeeService.updateEmployee(id, employee);
        if (employeeEntityOptional.isPresent()) {
            return ResponseEntity.ok(employeeEntityOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
