package co.develhope.progettoDiGruppoRecap.Repository;

import co.develhope.progettoDiGruppoRecap.Entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
