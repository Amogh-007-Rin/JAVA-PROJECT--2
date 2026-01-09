package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Library;
import java.time.LocalDate;

// FULLY IMPLEMENTED HELP CLASS TO DISPLAY HELP MESSAGE IN THE CLI
public class Help implements Command {

    @Override
    public void execute(Library library, LocalDate currentDate) {
        System.out.println(Command.HELP_MESSAGE);
    }
}
 