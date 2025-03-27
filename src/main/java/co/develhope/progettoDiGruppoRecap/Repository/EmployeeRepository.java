package co.develhope.progettoDiGruppoRecap.Repository;

import co.develhope.progettoDiGruppoRecap.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository <EmployeeEntity , Long> {
    //Ricerca dipendenti per stipendio
    List<EmployeeEntity> reserchBySalary(Long salary);

    //Ricerca dipendenti per range di stipendio
    List<EmployeeEntity> findBySalaryBetween(Long minSalary, Long maxSalary);

    //Ricerca dipendenti assunti in un periodo specifico
    List<EmployeeEntity> findByHiringPeriod(LocalDate periodoDiAssunzione);
}
