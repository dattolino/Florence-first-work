package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
    private @Id @GeneratedValue Long id;
    private String nome;
    private String cognome;
    private String mail;
    private String indirizzo;

    public User() {}

    public User(String nome, String cognome, String mail, String indirizzo) {
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.indirizzo = indirizzo;
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public User updateUser(User oldUser){
        User user = new User();
        user.setNome(oldUser.getNome());
        user.setCognome(oldUser.getCognome());
        user.setMail(oldUser.getMail());
        user.setIndirizzo(oldUser.getIndirizzo());

        return user;
    }
}
