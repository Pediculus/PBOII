/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.birthdaysomething;

/**
 *
 * @author HAK_PHENG
 */
import java.time.LocalDate;

public class Person {
    private String name;
    private LocalDate birthDate;

    public Person(String name, LocalDate birthDate){
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName(){
        return name;
    }

    public LocalDate getBirthDate(){
        return birthDate;
    }
}
