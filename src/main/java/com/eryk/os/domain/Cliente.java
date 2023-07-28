package com.eryk.os.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;


import java.util.ArrayList;
import java.util.List;
@Entity
public class Cliente extends Pessoa {

    @OneToMany(mappedBy = "cliente")
    private List<OS> list = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Integer id, String name, String cpf, String cellphone) {
        super(id, name, cpf, cellphone);
    }

    public List<OS> getList() {
        return list;
    }

    public void setList(List<OS> list) {
        this.list = list;
    }
}
