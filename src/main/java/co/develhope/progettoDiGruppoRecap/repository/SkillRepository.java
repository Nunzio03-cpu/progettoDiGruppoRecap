package co.develhope.progettoDiGruppoRecap.Repository;


import co.develhope.progettoDiGruppoRecap.Entity.EmployeeEntity;
import co.develhope.progettoDiGruppoRecap.Entity.LivelloEnum;
import co.develhope.progettoDiGruppoRecap.Entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByNome(String nome);
    List<Skill> findByLivelloEnum(LivelloEnum livelloEnum);
     @Query(value="select count(e.id) " +
             "from employee e " +
             "join employee_skill es on e.id = es.employee_id " +
             "join skill s on es.skill_id = s.id " +
             "where s.nome = ?1;", nativeQuery = true)
    Long findByEmployeeEntityCountSkill(String nomeSkill);

    @Query(value="select e.* " +
            "from employee e " +
            "join employee_skill es on e.id = es.employee_id " +
            "join skill s on es.skill_id = s.id " +
            "where s.nome = ?1;", nativeQuery = true)
    List<EmployeeEntity> findByEmployeeEntityList(String nomeSkill);






}
