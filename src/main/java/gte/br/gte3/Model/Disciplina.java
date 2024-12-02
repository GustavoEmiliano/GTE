package gte.br.gte3.Model;

import jakarta.persistence.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    // Relacionamento com tarefas (assumindo que uma disciplina tem muitas tarefas)
    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    private List<Tarefa> tarefas;



    public Disciplina(String nome) {
        this.nome = nome;
    }

    public Disciplina(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }


    @Override
    public String toString() {
        String result = "Disciplina{" + "nome='" + nome + '\'' + '}';
        System.out.println(result);
        return nome;
    }
}