package com.nicolas.tarefas_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private boolean feita;

    private LocalDate dataEntrega;

    public Tarefa() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public boolean isFeita() { return feita; }
    public void setFeita(boolean feita) { this.feita = feita; }

    public LocalDate getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(LocalDate dataEntrega) { this.dataEntrega = dataEntrega; }
}
