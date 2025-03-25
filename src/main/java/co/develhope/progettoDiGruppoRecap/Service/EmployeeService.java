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
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            optionalEmployee.get().setName(employee.getName());
            optionalEmployee.get().setLastName(employee.getLastName());
            optionalEmployee.get().setSalary(employee.getSalary());
            optionalEmployee.get().setDateOfBirth(employee.getDateOfBirth());
            optionalEmployee.get().setEmail(employee.getEmail());
            optionalEmployee.get().setSalary(employee.getSalary());
            EmployeeEntity employeeEntity = employeeRepository.save(employee);
            return Optional.of(employeeEntity);
        }
        return Optional.empty();
    }
}
