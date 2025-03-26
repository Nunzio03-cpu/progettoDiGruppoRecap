package co.develhope.progettoDiGruppoRecap.repository;

import co.develhope.progettoDiGruppoRecap.entity.LivelloEnum;
import co.develhope.progettoDiGruppoRecap.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByNome(String nome);
    List<Skill> findByLivelloEnum(LivelloEnum livelloEnum);

}
