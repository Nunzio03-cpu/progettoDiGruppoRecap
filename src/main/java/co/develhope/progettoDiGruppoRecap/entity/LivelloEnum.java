package co.develhope.progettoDiGruppoRecap.entity;


public enum LivelloEnum {
    JUNIOR("livello principiante"),
    MEDIUM("livello intermedio"),
    MASTER("livello avanzato");

    private final String dettagliLivello;

    LivelloEnum(String dettagliLivello) {
        this.dettagliLivello = dettagliLivello;
    }

    public String getDettagliLivello() {
        return dettagliLivello;
    }
}
