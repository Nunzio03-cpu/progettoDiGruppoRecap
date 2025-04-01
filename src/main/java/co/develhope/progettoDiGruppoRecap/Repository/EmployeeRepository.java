package co.develhope.progettoDiGruppoRecap.Repository;

import co.develhope.progettoDiGruppoRecap.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository <EmployeeEntity , Long> {
    //Ricerca dipendenti per stipendio
    List<EmployeeEntity> findBySalary(Long salary);

    //Ricerca dipendenti per range di stipendio
    List<EmployeeEntity> findBySalaryBetween(Long minSalary, Long maxSalary);

    //Ricerca dipendenti assunti in un periodo specifico
    List<EmployeeEntity> findByDateOfBirth(LocalDate periodoDiAssunzione);

    @Query(value = "select count(*) from employee e join department d on e.department = ?1;", nativeQuery = true)
    Long countNumberOfEmployeesForDepartment(Long idDepartment);
}
