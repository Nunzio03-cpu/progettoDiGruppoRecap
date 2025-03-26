package co.develhope.progettoDiGruppoRecap.Service;

import co.develhope.progettoDiGruppoRecap.Entity.DepartmentEntity;
import co.develhope.progettoDiGruppoRecap.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Optional<DepartmentEntity> update(Long id, DepartmentEntity updatedDepartmentEntity){
        Optional<DepartmentEntity> departmentEntityOptional = departmentRepository.findById(id);
        if(departmentEntityOptional.isPresent()){
            departmentEntityOptional.get().setName(updatedDepartmentEntity.getName());
            departmentEntityOptional.get().setManager(updatedDepartmentEntity.getManager());
            departmentEntityOptional.get().setDescription(updatedDepartmentEntity.getDescription());
            departmentEntityOptional.get().setLocation(updatedDepartmentEntity.getLocation());
            departmentEntityOptional.get().setBudget(updatedDepartmentEntity.getBudget());
            departmentEntityOptional.get().setDateCreated(updatedDepartmentEntity.getDateCreated());
            departmentEntityOptional.get().setStatusEnum(updatedDepartmentEntity.getStatusEnum());
            departmentEntityOptional.get().setPhone(updatedDepartmentEntity.getPhone());
            departmentEntityOptional.get().setEmail(updatedDepartmentEntity.getEmail());
            DepartmentEntity departmentEntity = departmentEntityOptional.get();
            departmentRepository.save(departmentEntity);
            return Optional.of(departmentEntity);
        } else {
            return Optional.empty();
        }
    }

    public DepartmentEntity delete(DepartmentEntity departmentEntity){
        departmentRepository.delete(departmentEntity);
        return departmentEntity;
    }

    public List<DepartmentEntity> findByDateCreated(LocalDate dateCreated){
        List<DepartmentEntity> departmentEntities = departmentRepository.findByDateCreated(dateCreated);
        return departmentEntities;
    }

    public List<DepartmentEntity> findByNameContaining(String name){
        List<DepartmentEntity> departmentEntities = departmentRepository.findByNameContaining(name);
        return departmentEntities;
    }
}
