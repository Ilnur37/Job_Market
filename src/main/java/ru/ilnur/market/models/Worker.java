package ru.ilnur.market.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "Workers")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    private String gender;

    @Min(value = 18, message = "Age should be grater than 17")
    private int age;

    private String address;

    private int education;

    private String speciality;

    @Min(value = 0, message = "Incorrect value")
    private int experience;

    private int english;

    @Column(name = "prog_lang")
    private int progLang;

    private int car;

    private int computer;

    public Worker(String name, String gender, int age,
                  String address, int education,
                  String speciality, int experience,
                  int english, int progLang,
                  int car, int computer) {
        this.name = name;
        this.gender = gender;
        this.age = age;
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