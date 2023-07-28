package com.eryk.os.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Tecnico extends Pessoa implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<OS> list = new ArrayList<>();


    public Tecnico() {
    }

    public Tecnico(Integer id, String name, String cpf, String cellphone) {
        super(id, name, cpf, cellphone);
    }

    public List<OS> getList() {
        return list;
    }

    public void setList(List<OS> list) {
        this.list = list;
    }
}
