package co.develhope.progettoDiGruppoRecap.Service;

import co.develhope.progettoDiGruppoRecap.Entity.EmployeeEntity;
import co.develhope.progettoDiGruppoRecap.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    public EmployeeEntity createEmployee(EmployeeEntity employee) {
        return employeeRepository.save(employee);

    }

    public List<EmployeeEntity> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<EmployeeEntity> updateEmployee(Integer id, EmployeeEntity employee) {
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

}
