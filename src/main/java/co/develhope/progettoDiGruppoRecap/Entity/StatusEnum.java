package co.develhope.progettoDiGruppoRecap.Entity;

public enum StatusEnum {
    ATTIVO ("status attivo"),
    INATTIVO ("status inattivo"),
    SOSPESO ("status sospeso");

    private String descrizione;

    StatusEnum(String descrizione) {
        this.descrizione = descrizione;
    }
}
