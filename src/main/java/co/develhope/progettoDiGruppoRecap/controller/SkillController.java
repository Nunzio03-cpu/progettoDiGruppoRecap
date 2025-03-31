package co.develhope.progettoDiGruppoRecap.Controller;

import co.develhope.progettoDiGruppoRecap.Entity.EmployeeEntity;
import co.develhope.progettoDiGruppoRecap.Entity.LivelloEnum;
import co.develhope.progettoDiGruppoRecap.Entity.Skill;
import co.develhope.progettoDiGruppoRecap.Service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skills")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @PostMapping("/create")
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill){
        Skill create = skillService.createSkill(skill);
        return ResponseEntity.ok(create);
    }

    @GetMapping("/get-all")
    public List<Skill> getAllSkills(){
        return skillService.getAllSKills();
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Optional<Skill>> findById(@PathVariable Long id){
        Optional<Skill> skillOptional = skillService.findById(id);
        if(skillOptional.isPresent()){
            return ResponseEntity.ok(skillOptional);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Skill>> updateSkill(@PathVariable Long id, @RequestBody Skill skillUpdate){
        Optional<Skill> skillOptional = skillService.updateSkill(id, skillUpdate);
        if(skillOptional.isPresent()){
            return ResponseEntity.ok(skillOptional);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        skillService.deleteSkill(id);
    }

    @GetMapping("/search-by-nome")
    public ResponseEntity<List<Skill>> searchByNome(@RequestParam String nome){
        List<Skill> list = skillService.searchByNome(nome);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/search-by-livello/{livelloEnum}")
    public ResponseEntity<List<Skill>> searchByLivelloEnum(@PathVariable LivelloEnum livelloEnum){
        List<Skill> list = skillService.searchByLivelloEnum(livelloEnum);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/count-employee")
    public ResponseEntity<Long> countEmployeesBySkill(@RequestParam String skillName){
        Long count = skillService.countEmployeesBySkill(skillName);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/list-employee")
    public  ResponseEntity<List<EmployeeEntity>> employeeEntityList(@RequestParam String skillName){
        List<EmployeeEntity> list = skillService.employeeEntityList(skillName);
        return ResponseEntity.ok(list);
    }

}
