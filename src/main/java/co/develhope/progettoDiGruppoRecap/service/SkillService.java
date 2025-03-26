package co.develhope.progettoDiGruppoRecap.service;

import co.develhope.progettoDiGruppoRecap.entity.LivelloEnum;
import co.develhope.progettoDiGruppoRecap.entity.Skill;
import co.develhope.progettoDiGruppoRecap.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public Skill createSkill(Skill skill){
        return skillRepository.save(skill);
    }

    public List<Skill> getAllSKills(){
        return skillRepository.findAll();
    }

    public Optional<Skill> findById(Long id){
        Optional<Skill> skillOptional = skillRepository.findById(id);
        if(skillOptional.isPresent()){
            return skillOptional;

        } else{
            return Optional.empty();
        }
    }

    public Optional<Skill> updateSkill(Long id, Skill skillUpdate){
        Optional<Skill> skillOptional = skillRepository.findById(id);
        if(skillOptional.isPresent()){
            skillOptional.get().setNome(skillUpdate.getNome());
            skillOptional.get().setDescrizione(skillUpdate.getDescrizione());
            skillOptional.get().setLivelloEnum(skillUpdate.getLivelloEnum());
            Skill skillNew = skillRepository.save(skillOptional.get());
            return Optional.of(skillNew);
        } else{
            return Optional.empty();
        }
    }

    public void deleteSkill(Long id){
        skillRepository.deleteById(id);
    }

    public List<Skill> searchByNome(String nome){
        return skillRepository.findByNome(nome);
    }

    public List<Skill> searchByLivelloEnum(LivelloEnum livelloEnum){
        return skillRepository.findByLivelloEnum(livelloEnum);
    }





}
