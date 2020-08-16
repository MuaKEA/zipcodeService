package com.md.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Zipcode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private int nr;
    private String navn;


    public Zipcode() {
    }

    public Zipcode(int nr, String navn) {
        this.nr = nr;
        this.navn = navn;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
}