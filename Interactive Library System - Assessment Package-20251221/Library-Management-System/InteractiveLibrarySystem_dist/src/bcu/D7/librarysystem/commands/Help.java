package bcu.D7.librarysystem.commands;

import java.time.LocalDate;

import bcu.D7.librarysystem.model.Library;

// FULLY IMPLEMENTED HELP CLASS TO DISPLAY HELP MESSAGE IN THE CLI
public class Help implements Command {

    @Override
    public void execute(Library library, LocalDate currentDate) {
        System.out.println(Command.HELP_MESSAGE);
    }
}
 