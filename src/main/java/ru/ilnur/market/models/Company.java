package ru.ilnur.market.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "Companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String address;

    private int education;

    private String speciality;

    private int experience;

    private int english;

    @Column(name = "prog_lang")
    private int progLang;

    private int car;

    private int computer;

    public Company(String name, String address,
                   int education, String speciality,
                   int experience, int english,
                   int progLang, int car, int computer) {
        this.name = name;
        this.address = address;
        this.education = education;
        this.speciality = speciality;
        this.experience = experience;
        this.english = english;
        this.progLang = progLang;
        this.car = car;
        this.computer = computer;
    }
}