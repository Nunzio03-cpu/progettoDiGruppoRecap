package co.develhope.progettoDiGruppoRecap.Entity;
import jakarta.persistence.*;


@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descrizione")
    private String descrizione;
    @Column(name = "livello")
    @Enumerated(EnumType.STRING)
    private LivelloEnum livelloEnum;

    public Skill() {
    }

    public Skill(Long id, String nome, String descrizione, LivelloEnum livelloEnum) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.livelloEnum = livelloEnum;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LivelloEnum getLivelloEnum() {
        return livelloEnum;
    }

    public void setLivelloEnum(LivelloEnum livelloEnum) {
        this.livelloEnum = livelloEnum;
    }


}
