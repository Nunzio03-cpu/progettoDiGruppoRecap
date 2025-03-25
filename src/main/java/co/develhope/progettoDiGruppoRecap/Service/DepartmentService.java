package co.develhope.progettoDiGruppoRecap.Service;

import co.develhope.progettoDiGruppoRecap.Entity.DepartmentEntity;
import co.develhope.progettoDiGruppoRecap.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentEntity createDepartment(DepartmentEntity departmentEntity){
        return departmentRepository.save(departmentEntity);
    }

}
