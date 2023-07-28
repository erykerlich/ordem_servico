package com.eryk.os.dtos;

import com.eryk.os.domain.Tecnico;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serial;
import java.io.Serializable;

public class TecnicoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotEmpty(message = "O campo NOME é obrigatorio !")
    private String name;
    @CPF
    @NotEmpty(message = "O campo CPF é obrigatorio !")
    private String cpf;
    @NotEmpty(message = "O campo TELEFONE é obrigatorio !")
    private String cellphone;

    public TecnicoDTO() {
    }

    public TecnicoDTO(Tecnico obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.cpf = obj.getCpf();
        this.cellphone = obj.getCellphone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
}
