package co.develhope.progettoDiGruppoRecap;

import co.develhope.progettoDiGruppoRecap.Controller.SkillController;
import co.develhope.progettoDiGruppoRecap.Entity.LivelloEnum;
import co.develhope.progettoDiGruppoRecap.Entity.Skill;
import co.develhope.progettoDiGruppoRecap.Service.SkillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import co.develhope.progettoDiGruppoRecap.Controller.DepartmentController;
import co.develhope.progettoDiGruppoRecap.Entity.DepartmentEntity;
import co.develhope.progettoDiGruppoRecap.Service.DepartmentService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;
import java.time.LocalDate;
import java.util.Collections;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Optional;

@AutoConfigureMockMvc
class ProgettoDiGruppoRecapApplicationTests {
	@Autowired
	private SkillController skillController;
	@Autowired
	private TestRestTemplate testRestTemplate;
	@MockitoBean
	private SkillService skillService;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
    @Autowired
    private DepartmentController departmentController;
    @MockitoBean
    private DepartmentService departmentService;
    private DepartmentEntity departmentEntity;

	private Skill skill;


	@Test
	public void testCreateSkill() throws Exception{
		when(skillService.createSkill(any(Skill.class))).thenReturn(skill);
		mockMvc.perform(post("/skills/create"));


	}




    @BeforeEach
    public void setUp() {
        departmentEntity = new DepartmentEntity();
        departmentEntity.setId(1L);
        departmentEntity.setName("Department");
        departmentEntity.setManager("Manager");
        departmentEntity.setDescription("Description");
        departmentEntity.setLocation("Location");
        departmentEntity.setBudget(1.0);
        departmentEntity.setDateCreated(LocalDate.now());
        departmentEntity.setPhone("000 000 0000");
        departmentEntity.setEmail("email.company@gmail.com");

        skill = new Skill();
        skill.setId(1L);
        skill.setNome("Programma per test");
        skill.setDescrizione("skill da testare");
        skill.setLivelloEnum(LivelloEnum.MEDIUM);
    }

    @Test
    public void testCreateDepartment() throws Exception {
        when(departmentService.createDepartment(any(DepartmentEntity.class))).thenReturn(departmentEntity);
        mockMvc.perform(post("/department/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departmentEntity)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(departmentEntity.getName()));
    }

    @Test
    public void testSelectAllDepartments() throws Exception {
        when(departmentService.selectAllDepartments()).thenReturn(Collections.singletonList(departmentEntity));
        mockMvc.perform(get("/department/select-all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departmentEntity)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testSelectDepartmentByIdOk() throws Exception {
        when(departmentService.selectById(anyLong())).thenReturn(Optional.of(departmentEntity));
        mockMvc.perform(get("/department/select-by-id/" + departmentEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departmentEntity)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(departmentEntity.getName()));
    }

    @Test
    public void testSelectDepartmentByIdNotFound() throws Exception {
        when(departmentService.selectById(anyLong())).thenReturn(Optional.empty());
        mockMvc.perform(get("/department/select-by-id/" + departmentEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departmentEntity)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateDepartmentOk() throws Exception {
        when(departmentService.update(anyLong(), any(DepartmentEntity.class)))
                .thenReturn(Optional.of(departmentEntity));
        mockMvc.perform(put("/department/update/" + departmentEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departmentEntity)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(departmentEntity.getName()));
    }

    @Test
    public void testUpdateDepartmentNotFound() throws Exception {
        when(departmentService.update(anyLong(), any(DepartmentEntity.class))).thenReturn(Optional.empty());
        mockMvc.perform(put("/department/update/" + departmentEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departmentEntity)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteDepartment() throws Exception {
        when(departmentService.delete(any(DepartmentEntity.class))).thenReturn(departmentEntity);
        mockMvc.perform(delete("/department/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(departmentEntity)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(departmentEntity.getName()));
    }

    @Test
    public void testFindDepartmentsByDateCreated() throws Exception {
        when(departmentService.findByDateCreated(departmentEntity.getDateCreated()))
                .thenReturn(Collections.singletonList(departmentEntity));
        mockMvc.perform(get("/department/find-by-datecreated")
                .param("dateCreated", departmentEntity.getDateCreated().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]name").value(departmentEntity.getName()));
    }

    @Test
    public void testFindDepartmentsByNameContaining() throws Exception {
        when(departmentService.findByNameContaining(anyString()))
                .thenReturn(Collections.singletonList(departmentEntity));
        mockMvc.perform(get("/department/find-by-name-containing")
                .param("name", departmentEntity.getName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]name").value(departmentEntity.getName()));
    }
}