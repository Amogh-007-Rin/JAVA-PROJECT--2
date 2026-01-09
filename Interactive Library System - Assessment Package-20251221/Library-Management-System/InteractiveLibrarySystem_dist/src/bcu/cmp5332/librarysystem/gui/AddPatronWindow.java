package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.AddPatron;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

// FULLY IMPLEMENTED ADD-PATRON-WINDOW CLASS TO ADD A PATRON INTO THE LIBRARY USING GUI APPLICATION
public class AddPatronWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField nameText = new JTextField();
    private JTextField phoneText = new JTextField();
    private JTextField emailText = new JTextField();

    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");

    public AddPatronWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Add a New Patron");

        setSize(300, 180);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(4, 2));
        topPanel.add(new JLabel("Name : "));
        topPanel.add(nameText);
        topPanel.add(new JLabel("Phone : "));
        topPanel.add(phoneText);
        topPanel.add(new JLabel("Email : "));
        topPanel.add(emailText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(addBtn);
        bottomPanel.add(cancelBtn);

        addBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) {
            addPatron();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void addPatron() {
        try {
            String name = nameText.getText().trim();
            String phone = phoneText.getText().trim();
            String email = emailText.getText().trim();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(this, "Name field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (phone.length() == 0) {
                JOptionPane.showMessageDialog(this, "Phone field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (email.length() == 0) {
                JOptionPane.showMessageDialog(this, "Email field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int rc = JOptionPane.showConfirmDialog(this, "Add patron '" + name + "' with phone '" + phone + "'?", "Confirm Add", JOptionPane.YES_NO_OPTION);
            if(rc != JOptionPane.YES_OPTION) return;
            // create and execute the AddPatron Command
            Command addPatron = new AddPatron(name, phone, email, false);
            addPatron.execute(mw.getLibrary(), LocalDate.now());                
            // refresh the view with the list of patrons
            mw.displayPatrons();
            // hide (close) the AddPatronWindow
            this.setVisible(false);
        } catch (LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
