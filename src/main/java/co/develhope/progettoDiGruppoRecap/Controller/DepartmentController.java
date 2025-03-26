package co.develhope.progettoDiGruppoRecap.Controller;

import co.develhope.progettoDiGruppoRecap.Entity.DepartmentEntity;
import co.develhope.progettoDiGruppoRecap.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<DepartmentEntity> createDepartment(@RequestBody DepartmentEntity departmentEntity){
        return ResponseEntity.ok(departmentService.createDepartment(departmentEntity));
    }

    @GetMapping("/select-all")
    public ResponseEntity<List<DepartmentEntity>> selectAllDepartments() {
        return ResponseEntity.ok(departmentService.selectAllDepartments());
    }

    @GetMapping("/select-by-id/{id}")
    public ResponseEntity<Optional<DepartmentEntity>> selectById(@PathVariable Long id){
        Optional<DepartmentEntity> departmentEntityOptional = departmentService.selectById(id);
        if (departmentEntityOptional.isPresent()){
            return ResponseEntity.ok(departmentEntityOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<DepartmentEntity>> update(@PathVariable Long id,
                                                             @RequestBody DepartmentEntity departmentEntity){
        Optional<DepartmentEntity> departmentEntityOptional = departmentService.update(id, departmentEntity);
        if (departmentEntityOptional.isPresent()){
            return ResponseEntity.ok(departmentEntityOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DepartmentEntity> delete(@RequestBody DepartmentEntity departmentEntity){
        departmentService.delete(departmentEntity);
        return ResponseEntity.ok(departmentEntity);
    }

    @GetMapping("/find-by-datecreated")
    public ResponseEntity<List<DepartmentEntity>> findByDateCreated(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateCreated) {
        return ResponseEntity.ok(departmentService.findByDateCreated(dateCreated));
    }
}
