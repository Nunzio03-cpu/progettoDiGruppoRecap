package co.develhope.progettoDiGruppoRecap.Repository;

import co.develhope.progettoDiGruppoRecap.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository <EmployeeEntity , Long> {
    //Ricerca dipendenti per stipendio
}
