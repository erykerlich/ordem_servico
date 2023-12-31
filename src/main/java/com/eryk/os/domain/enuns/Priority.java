package com.eryk.os.domain.enuns;

public enum Priority {

    LOW(0, "BAIXA"),
    MEDIA(1, "MEDIA"),
    HIGH(2, "ALTA");

    private Integer cod;
    private String description;

    Priority(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static Priority toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }

        for (Priority x : Priority.values()) {
            if(cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Prioridade invalida !"+ cod);
    }


}
