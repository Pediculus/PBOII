/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.birthdaysomething;

/**
 *
 * @author HAK_PHENG
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;

class BirthdayController{
    private BirthdayGUI view;

    public BirthdayController(BirthdayGUI view){
        this.view = view;
        
        this.view.calculateButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String name = view.nameField.getText().trim();
                String dateText = view.dateField.getText().trim();

                try {
                    LocalDate birthDate = LocalDate.parse(dateText);
                    Person person = new Person(name, birthDate);
                    view.resultArea.setText(calculateInfo(person));
                } catch (Exception ex) {
                    view.resultArea.setText("Format Tanggal Salah. Gunakan yyyy-mm-dd.");
                }
            }
        });
    }

    private String calculateInfo(Person person){
        LocalDate today = LocalDate.now();
        LocalDate birthDate = person.getBirthDate();

        Period age = Period.between(birthDate, today);

        LocalDate nextbirthDay = birthDate.withYear(today.getYear());
        if(nextbirthDay.isBefore(today) || nextbirthDay.equals(today)){
            nextbirthDay = nextbirthDay.plusYears(1);
        }

        long daysToNextBirthday = Duration.between(today.atStartOfDay(), nextbirthDay.atStartOfDay()).toDays();

        return String.format("""
        Halo %s!
        Umurmu saat ini: %d tahun, %d bulan, %d hari
        Hari menuju ulang tahun berikutnya: %d hari
        """, person.getName(), age.getYears(), age.getMonths(), age.getDays(), daysToNextBirthday);


    }
}
