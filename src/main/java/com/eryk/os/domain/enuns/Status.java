package com.eryk.os.domain.enuns;

public enum Status {

    OPEN(0, "ABERTO"),
    PROGRESS(1, "ANDAMENTOS"),
    CLOSED(2, "ENCERRADO");

    private Integer cod;
    private String description;

    Status(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static Status toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }

        for (Status x : Status.values()) {
            if(cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Status invalido !"+ cod);
    }
}
