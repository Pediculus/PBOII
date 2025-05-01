package com.mycompany.birthdaysomething;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;

// This class serves as the controller in the MVC architecture, handling user interactions and updating the view accordingly.
class BirthdayController {
    private BirthdayGUI view;  // Reference to the GUI (view)

    // Constructor accepts a BirthdayGUI object, which is used to access the GUI components.
    public BirthdayController(BirthdayGUI view) {
        this.view = view;
        
        // ActionListener for the "Calculate" button
        this.view.calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get user input from the name and date fields
                String name = view.nameField.getText().trim();
                String dateText = view.dateField.getText().trim();

                try {
                    // Try to parse the input date into a LocalDate object
                    LocalDate birthDate = LocalDate.parse(dateText);
                    // Create a new Person object with the entered data
                    Person person = new Person(name, birthDate);
                    // Call the calculateInfo method to generate the result and display it
                    view.resultArea.setText(calculateInfo(person));
                } catch (Exception ex) {
                    // If there's an error (e.g., invalid date format), display an error message
                    view.resultArea.setText("Format Tanggal Salah. Gunakan yyyy-mm-dd.");
                }
            }
        });
    }

    // Method to calculate age and the number of days until the next birthday
    private String calculateInfo(Person person) {
        LocalDate today = LocalDate.now();  // Get today's date
        LocalDate birthDate = person.getBirthDate();  // Get the person's birth date

        // Calculate the period between the birth date and today's date
        Period age = Period.between(birthDate, today);

        // Calculate the date of the next birthday
        LocalDate nextbirthDay = birthDate.withYear(today.getYear());
        if (nextbirthDay.isBefore(today) || nextbirthDay.equals(today)) {
            nextbirthDay = nextbirthDay.plusYears(1);  // If the birthday has passed or is today, set it to next year
        }

        // Calculate the number of days until the next birthday
        long daysToNextBirthday = Duration.between(today.atStartOfDay(), nextbirthDay.atStartOfDay()).toDays();

        // Return a formatted string with the person's age and the days to their next birthday
        return String.format("""
        Halo %s!
        Umurmu saat ini: %d tahun, %d bulan, %d hari
        Hari menuju ulang tahun berikutnya: %d hari
        """, person.getName(), age.getYears(), age.getMonths(), age.getDays(), daysToNextBirthday);
    }
}
