package co.develhope.progettoDiGruppoRecap.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "manager")
    private String managaer;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "budget")
    private Double budget;

    @Column(name = "date_created")
    private LocalDate dateCreated = LocalDate.now();

    @Column(name = "status")
    private StatusEnum statusEnum = StatusEnum.ATTIVO;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    public DepartmentEntity(){}

    public DepartmentEntity(String name, String managaer, Long id, String description, String location, Double budget, String phone, String email) {
        this.name = name;
        this.managaer = managaer;
        this.id = id;
        this.description = description;
        this.location = location;
        this.budget = budget;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagaer() {
        return managaer;
    }

    public void setManagaer(String managaer) {
        this.managaer = managaer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}