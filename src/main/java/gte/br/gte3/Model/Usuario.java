package gte.br.gte3.Model;

import java.util.ArrayList;

import jakarta.persistence.*;

@Entity
@Table
public class Usuario{

    //Esta classe é responsavel pela autenticação do usuário

    //Atributos da classe Usuario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id; //Chave identificadora do usuario
    @Column
    private String Username; //Nome de usuario do usuario
    @Column
    private String  Password; //Senha do usuario para login
    @Column
    private String Email; // Correio eletronico do usuario para login
    @Column
    private String Nome; //Primeiro nome do usuario
    @Column
    private String Sobrenome; // Sobrenome do usuario

    private static ArrayList<Tarefa> tarefas; //Usuário terá uma lista de tarefas que precisará ser feita


    //construtor



    public Usuario(String username, String password, String email, String nome, String sobrenome) {
        Username = username;
        Password = password;
        Email = email;
        Nome = nome;
        Sobrenome = sobrenome;
    }

    public Usuario() {

    }

    public void removerTarefa(Tarefa tarefa) {
        if (tarefas == null) {
            tarefas = new ArrayList<>();
        }
        tarefas.remove(tarefa);
    }

    //getters e setters
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        Sobrenome = sobrenome;
    }

    public static ArrayList<Tarefa> getTarefas() {
        return tarefas;
    }




    @Override
    public String toString() {
        return "Usuario{" +
                "Id=" + Id +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", Email='" + Email + '\'' +
                ", Nome='" + Nome + '\'' +
                ", Sobrenome='" + Sobrenome + '\'' +
                ", tarefas=" + tarefas +
                '}';
    }
}