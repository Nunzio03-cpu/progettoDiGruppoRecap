package co.develhope.progettoDiGruppoRecap.Service;

import co.develhope.progettoDiGruppoRecap.Entity.DepartmentEntity;
import co.develhope.progettoDiGruppoRecap.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentEntity createDepartment(DepartmentEntity departmentEntity){
        return departmentRepository.save(departmentEntity);
    }

    public List<DepartmentEntity> selectAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<DepartmentEntity> selectById(Long id){
        Optional<DepartmentEntity> departmentEntityOptional = departmentRepository.findById(id);
        if(departmentEntityOptional.isPresent()){
            return departmentEntityOptional;
        } else {
            return Optional.empty();
        }
    }
}
