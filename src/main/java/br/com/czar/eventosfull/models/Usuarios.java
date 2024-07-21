package br.com.czar.eventosfull.models;

import jakarta.persistence.*;



@Entity
@Table (name = "usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String email;
    private String nome;
    private String senha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer cpf) {
        this.id = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
