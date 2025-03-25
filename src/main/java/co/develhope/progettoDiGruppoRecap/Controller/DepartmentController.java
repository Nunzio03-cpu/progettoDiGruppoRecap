package co.develhope.progettoDiGruppoRecap.Controller;

import co.develhope.progettoDiGruppoRecap.Entity.DepartmentEntity;
import co.develhope.progettoDiGruppoRecap.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<DepartmentEntity> createDepartment(@RequestBody DepartmentEntity departmentEntity){
        return ResponseEntity.ok(departmentService.createDepartment(departmentEntity));
    }
}
