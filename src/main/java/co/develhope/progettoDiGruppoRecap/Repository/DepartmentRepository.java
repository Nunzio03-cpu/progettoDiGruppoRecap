package co.develhope.progettoDiGruppoRecap.Repository;

import co.develhope.progettoDiGruppoRecap.Entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    List<DepartmentEntity> findByDateCreated(LocalDate dateCreated);
}
