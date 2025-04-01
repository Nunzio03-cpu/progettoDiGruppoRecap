package co.develhope.progettoDiGruppoRecap.Service;

import co.develhope.progettoDiGruppoRecap.Entity.DepartmentEntity;
import co.develhope.progettoDiGruppoRecap.Entity.EmployeeEntity;
import co.develhope.progettoDiGruppoRecap.Repository.DepartmentRepository;
import co.develhope.progettoDiGruppoRecap.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;


    public EmployeeEntity createEmployee(EmployeeEntity employee) {
        return employeeRepository.save(employee);

    }

    public List<EmployeeEntity> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<EmployeeEntity> updateEmployee(Long id, EmployeeEntity employee) {
        Optional<EmployeeEntity> employeeDaAggiornare = employeeRepository.findById(id);
        if (employeeDaAggiornare.isPresent()) {
            employeeDaAggiornare.get().setName(employee.getName());
            employeeDaAggiornare.get().setLastName(employee.getLastName());
            employeeDaAggiornare.get().setSalary(employee.getSalary());
            employeeDaAggiornare.get().setDateOfBirth(employee.getDateOfBirth());
            employeeDaAggiornare.get().setEmail(employee.getEmail());
            employeeDaAggiornare.get().setSalary(employee.getSalary());
            EmployeeEntity employeeEntity = employeeRepository.save(employeeDaAggiornare.get());
            return Optional.of(employeeEntity);
        }
        return Optional.empty();
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    public List<EmployeeEntity> findBySalary(Long salary) {
        return employeeRepository.findBySalary(salary);
    }

    public List<EmployeeEntity> findBySalaryRange(Long minSalary, Long maxSalary) {
        return employeeRepository.findBySalaryBetween(minSalary, maxSalary);
    }

    public List<EmployeeEntity> findByHiringPeriod(LocalDate hiringPeriod) {
        return employeeRepository.findByDateOfBirth(hiringPeriod);
    }

    public Optional<EmployeeEntity> findById(Long id){
        return employeeRepository.findById(id);
    }

    public Optional<EmployeeEntity> addDepartmentToEmployee(Long idDepartment, Long idEmployee) {
        //crea gli oggetti optional
        Optional<DepartmentEntity> departmentEntityOptional = departmentRepository.findById(idDepartment);
        Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(idEmployee);
        //controllo di esistenza
        if (departmentEntityOptional.isPresent() && employeeEntityOptional.isPresent()){
            DepartmentEntity departmentEntity = departmentEntityOptional.get();
            EmployeeEntity employeeEntity = employeeEntityOptional.get();
            //devo aggiungere il departmentEntity al campo depatment dell' employeeEntity
            employeeEntity.setDepartment(departmentEntity);
            //devo salvare in nuovo oggetto che si chiama employeeSaved tramite la EmployeeRepository
            //tramite il metodo save
            EmployeeEntity employeeSaved = employeeRepository.save(employeeEntity);
            return Optional.of(employeeSaved);
        } else {
            return Optional.empty();
        }
    }

    public Optional<EmployeeEntity> removeDepartmentFromEmployee(Long idDepartment, Long idEmployee) {
        Optional<DepartmentEntity> departmentEntityOptional = departmentRepository.findById(idDepartment);
        Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(idEmployee);
        if (departmentEntityOptional.isPresent() && employeeEntityOptional.isPresent()){
            DepartmentEntity departmentEntity = departmentEntityOptional.get();
            EmployeeEntity employeeEntity = employeeEntityOptional.get();
            employeeEntity.setDepartment(null);
            EmployeeEntity employeeSaved = employeeRepository.save(employeeEntity);
            return Optional.of(employeeSaved);
        } else {
            return Optional.empty();
        }
    }

    public Long countNumberOfEmployeesForDepartment(Long idDepartment){
        return employeeRepository.countNumberOfEmployeesForDepartment(idDepartment);
    }
}
