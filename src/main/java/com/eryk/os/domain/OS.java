package com.eryk.os.domain;

import com.eryk.os.domain.enuns.Priority;
import com.eryk.os.domain.enuns.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;


import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class OS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataOpen;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFinished;
    private Integer priority;
    private String observacoes;
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public OS() {
        this.setDataOpen(LocalDateTime.now());
        this.setPriority(Priority.LOW);
        this.setStatus(Status.OPEN);
    }

    public OS(Integer id, Priority priority, String observacoes, Status status, Tecnico tecnico, Cliente cliente) {
        this.id = id;
        this.setDataOpen(LocalDateTime.now());
        this.priority = (priority == null) ? 0 : priority.getCod();
        this.observacoes = observacoes;
        this.status = (status == null) ? 0 : status.getCod();
        this.tecnico = tecnico;
        this.cliente = cliente;
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

    public Priority getPriority() {
        return Priority.toEnum(this.priority);
    }

    public void setPriority(Priority priority) {
        this.priority = priority.getCod();
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String obeservacoes) {
        this.observacoes = obeservacoes;
    }

    public Status getStatus() {
        return Status.toEnum(this.status);
    }

    public void setStatus(Status status) {
        this.status = status.getCod();
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OS os = (OS) o;
        return Objects.equals(id, os.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
