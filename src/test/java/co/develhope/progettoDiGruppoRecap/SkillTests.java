package co.develhope.progettoDiGruppoRecap;

import co.develhope.progettoDiGruppoRecap.Controller.SkillController;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import co.develhope.progettoDiGruppoRecap.Entity.EmployeeEntity;
import co.develhope.progettoDiGruppoRecap.Entity.LivelloEnum;
import co.develhope.progettoDiGruppoRecap.Entity.Skill;
import co.develhope.progettoDiGruppoRecap.Service.SkillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Collections;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Optional;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SkillTests {
    @Autowired
    private SkillController skillController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockitoBean
    private SkillService skillService;
    @Autowired
    private ObjectMapper objectMapper;
    private Skill skill;
    private EmployeeEntity employeeEntity;

    @BeforeEach
    public void setUp() {
        skill = new Skill();
        skill.setId(1L);
        skill.setNome("Programma per test");
        skill.setDescrizione("skill da testare");
        skill.setLivelloEnum(LivelloEnum.MEDIUM);

        employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1L);
        employeeEntity.setName("Biagio");
    }

    // Test Skill
    @Test
    public void testCreateSkill() throws Exception {
        when(skillService.createSkill(any(Skill.class))).thenReturn(skill);
        mockMvc.perform(post("/skills/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(skill)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(skill.getNome()));
    }

    @Test
    public void testGetAllSkill() throws Exception {
        when(skillService.getAllSKills()).thenReturn(Collections.singletonList(skill));
        mockMvc.perform(get("/skills/select-all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(skill)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testSelectSkillByIdOk() throws Exception {
        when(skillService.findById(anyLong())).thenReturn(Optional.of(skill));
        mockMvc.perform(get("/skills/find-by-id/" + skill.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(skill)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(skill.getNome()));
    }

    @Test
    public void testSelectSkillByIdNotFound() throws Exception {
        when(skillService.findById(anyLong())).thenReturn(Optional.empty());
        mockMvc.perform(get("/skills/find-by-id/" + skill.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(skill)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateSkillOk() throws Exception {
        when(skillService.updateSkill(anyLong(), any(Skill.class)))
                .thenReturn(Optional.of(skill));
        mockMvc.perform(put("/skills/update/" + skill.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(skill)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(skill.getNome()));
    }

    @Test
    public void testUpdateSkillNotFound() throws Exception {
        when(skillService.updateSkill(anyLong(), any(Skill.class))).thenReturn(Optional.empty());
        mockMvc.perform(put("/skills/update/" + skill.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(skill)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteSkillById() throws Exception {
        doNothing().when(skillService).deleteSkill(skill.getId());
        mockMvc.perform(delete("/skills/delete/" + skill.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(skill)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testSearchByNomeIsOk() throws Exception{
        when(skillService.searchByNome("Programma per test")).thenReturn(Collections.singletonList(skill));
        mockMvc.perform(get("/skills/search-by-nome")
                        .param("nome", "Programma per test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value(skill.getNome()));
    }

    @Test
    public void testSearchByNomeIsEmpty() throws Exception{
        when(skillService.searchByNome("Programma per test")).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/skills/search-by-nome")
                        .param("nome", "Programma per test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    public void testSearchByLivelloEnumIsOk() throws Exception{
        when(skillService.searchByLivelloEnum(LivelloEnum.MEDIUM)).thenReturn(Collections.singletonList(skill));
        mockMvc.perform(get("/skills/search-by-livello/" + skill.getLivelloEnum()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(skill.getId()));
    }

    @Test
    public void testSearchByLivelloEnumIsEmpty() throws Exception{
        when(skillService.searchByLivelloEnum(LivelloEnum.MEDIUM)).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/skills/search-by-livello/" + skill.getLivelloEnum()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    public void testCountEmployeesBySkillIsOk() throws Exception{
        when(skillService.countEmployeesBySkill("Programma per test")).thenReturn(1L);
        mockMvc.perform(get("/skills/count-employee")
                        .param("skillName", "Programma per test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    @Test
    public void testCountEmployeesBySkillIsEmpty() throws Exception{
        when(skillService.countEmployeesBySkill("Programma per test")).thenReturn(0L);
        mockMvc.perform(get("/skills/count-employee")
                        .param("skillName", "Programma per test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("0"));
    }

    @Test
    public void testEmployeeEntityListIsOk() throws Exception{
        when(skillService.employeeEntityList("Programma per test")).thenReturn(Collections.singletonList(employeeEntity));
        mockMvc.perform(get("/skills/list-employee")
                        .param("skillName", "Programma per test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Biagio"));
    }

    @Test
    public void testEmployeeEntityListIsEmpty() throws Exception{
        when(skillService.employeeEntityList("Programma per test")).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/skills/list-employee")
                        .param("skillName", "Programma per test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

    }

}
