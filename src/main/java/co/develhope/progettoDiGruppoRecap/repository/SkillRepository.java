package co.develhope.progettoDiGruppoRecap.Repository;


import co.develhope.progettoDiGruppoRecap.Entity.LivelloEnum;
import co.develhope.progettoDiGruppoRecap.Entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByNome(String nome);
    List<Skill> findByLivelloEnum(LivelloEnum livelloEnum);

}
