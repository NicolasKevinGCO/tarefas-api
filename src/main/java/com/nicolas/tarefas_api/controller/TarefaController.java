package com.nicolas.tarefas_api.controller;

import com.nicolas.tarefas_api.model.Tarefa;
import com.nicolas.tarefas_api.repository.TarefaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaRepository tarefaRepository;

    public TarefaController(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    @GetMapping
    public List<Tarefa> listar() {
        return tarefaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscar(@PathVariable Long id) {
        return tarefaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody Tarefa tarefa) {
        Tarefa salvo = tarefaRepository.save(tarefa);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(salvo.getId()).toUri();
        return ResponseEntity.created(location).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa dados) {
        return tarefaRepository.findById(id).map(t -> {
            t.setDescricao(dados.getDescricao());
            t.setFeita(dados.isFeita());
            t.setDataEntrega(dados.getDataEntrega());
            Tarefa salvo = tarefaRepository.save(t);
            return ResponseEntity.ok(salvo);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!tarefaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        tarefaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}