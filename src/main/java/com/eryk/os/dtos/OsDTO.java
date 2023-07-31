package com.eryk.os.dtos;


import com.eryk.os.domain.OS;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;


import java.io.Serializable;
import java.time.LocalDateTime;

public class OsDTO implements Serializable {

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataOpen;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFinished;
    private Integer priority;
    @NotEmpty(message = "O campo OBSERVAÇÕES é obrigatorio !")
    private String observacoes;
    private Integer status;
    private Integer tecnico;
    private Integer cliente;

    public OsDTO() {
    }

    public OsDTO(OS obj) {
        this.id = obj.getId();
        this.dataOpen = obj.getDataOpen();
        this.dataFinished = obj.getDataFinished();
        this.priority = obj.getPriority().getCod();
        this.observacoes = obj.getObservacoes();
        this.status = obj.getStatus().getCod();
        this.tecnico = obj.getTecnico().getId();
        this.cliente = obj.getCliente().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataOpen() {
        return dataOpen;
    }

    public void setDataOpen(LocalDateTime dataOpen) {
        this.dataOpen = dataOpen;
    }

    public LocalDateTime getDataFinished() {
        return dataFinished;
    }

    public void setDataFinished(LocalDateTime dataFinished) {
        this.dataFinished = dataFinished;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }
}
