package co.develhope.progettoDiGruppoRecap.Controller;

import co.develhope.progettoDiGruppoRecap.Entity.DepartmentEntity;
import co.develhope.progettoDiGruppoRecap.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
