/*********************************
*  ITC – 5201 Database Programming Using Java – Assignment    4                                                 	         *

*  I declare that this assignment is my own work in accordance with Humber Academic Policy.        *

*  No part of this assignment has been copied manually or electronically from any other source       *

* (including web sites) or distributed to other students/social media.                                                        *
                                                                                                                                                                             
*  Name: Pelumi Owoshagba 	Student ID: N01574587 Date: 11/22/2023
*  Name: Chioma Kamalu 		Student ID: N01600998 Date: 11/22/2023
*  Name: Adekunle Omonihi  	Student ID: N01511618 Date: 11/22/2023
*****/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class Staff extends JFrame {
    private static final long serialVersionUID = 1L;
    private Connection connection;
    private JLabel connectionStatusLabel;
    private StaffDAO staffDAO = new StaffDAO();

    private JTextField idTextField;
    private JTextField lastNameTextField;
    private JTextField firstNameTextField;
    private JTextField miTextField;
    private JTextField addressTextField;
    private JTextField cityTextField;
    private JTextField stateTextField;
    private JTextField telephoneTextField;
    private JTextField emailTextField;

    public Staff() {
        setTitle("Staff Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        connection = DatabaseConnector.getConnection();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createTitledBorder("Staff Information"));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        row1.add(new JLabel("ID:"));
        idTextField = new JTextField(15);
        row1.add(idTextField);
        mainPanel.add(row1);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        row2.add(new JLabel("Last Name:"));
        lastNameTextField = new JTextField(15);
        row2.add(lastNameTextField);
        row2.add(new JLabel("First Name:"));
        firstNameTextField = new JTextField(15);
        row2.add(firstNameTextField);
        row2.add(new JLabel("MI:"));
        miTextField = new JTextField(2);
        row2.add(miTextField);
        mainPanel.add(row2);

        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        row3.add(new JLabel("Address:"));
        addressTextField = new JTextField(30);
        row3.add(addressTextField);
        mainPanel.add(row3);

        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        row4.add(new JLabel("State:"));
        stateTextField = new JTextField(10);
        row4.add(stateTextField);
        row4.add(new JLabel("City:"));
        cityTextField = new JTextField(10);
        row4.add(cityTextField);
        mainPanel.add(row4);

        JPanel row5 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        row5.add(new JLabel("Telephone:"));
        telephoneTextField = new JTextField(15);
        row5.add(telephoneTextField);
        mainPanel.add(row5);

        JPanel row6 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        row6.add(new JLabel("Email:"));
        emailTextField = new JTextField(30);
        row6.add(emailTextField);
        mainPanel.add(row6);

        add(mainPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton viewButton = new JButton("View");
        JButton insertButton = new JButton("Insert");
        JButton updateButton = new JButton("Update");
        JButton clearButton = new JButton("Clear");

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idTextField.getText();

                StaffRecord staffRecord = staffDAO.viewStaff(id);

                if (staffRecord != null) {
                    lastNameTextField.setText(staffRecord.getLastName());
                    firstNameTextField.setText(staffRecord.getFirstName());
                    miTextField.setText(staffRecord.getMi());
                    addressTextField.setText(staffRecord.getAddress());
                    cityTextField.setText(staffRecord.getCity());
                    stateTextField.setText(staffRecord.getState());
                    telephoneTextField.setText(staffRecord.getTelephone());
                    emailTextField.setText(staffRecord.getEmail());
                } else {
                    JOptionPane.showMessageDialog(Staff.this, "No staff record found with ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve data from text fields
                String id = idTextField.getText();
                String lastName = lastNameTextField.getText();
                String firstName = firstNameTextField.getText();
                String mi = miTextField.getText();
                String address = addressTextField.getText();
                String city = cityTextField.getText();
                String state = stateTextField.getText();
                String telephone = telephoneTextField.getText();
                String email = emailTextField.getText();

                // Create a StaffRecord object
                StaffRecord staffRecord = new StaffRecord(id, lastName, firstName, mi, address, city, state, telephone, email);

                // Start a try-catch block for transaction management
                try {
                    // Establish the connection
                    connection = DatabaseConnector.getConnection();
                    // Disable auto-commit
                    connection.setAutoCommit(false);

                    // Insert record
                    boolean success = staffDAO.insertStaff(staffRecord);

                    if (success) {
                        // Commit the transaction
                        connection.commit();
                        JOptionPane.showMessageDialog(Staff.this, "A record is successfully inserted with ID: " + id, "Record Inserted", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Handle insert failure
                        JOptionPane.showMessageDialog(Staff.this, "Failed to insert record with ID: " + id, "Insertion Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Handle SQL exception
                    try {
                        // Rollback the transaction in case of an exception
                        if (connection != null) {
                            connection.rollback();
                        }
                        JOptionPane.showMessageDialog(Staff.this, "Transaction rolled back. Error inserting record.", "Transaction Error", JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException rollbackException) {
                        rollbackException.printStackTrace();
                        // Handle rollback exception
                    }
                } finally {
                    try {
                        // Close the connection
                        if (connection != null && !connection.isClosed()) {
                            connection.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        // Handle connection closure exception
                    }
                }

                // Clear text fields after successful insertion or handle failure
                clearTextFields();
            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idTextField.getText();
                String lastName = lastNameTextField.getText();
                String firstName = firstNameTextField.getText();
                String mi = miTextField.getText();
                String address = addressTextField.getText();
                String city = cityTextField.getText();
                String state = stateTextField.getText();
                String telephone = telephoneTextField.getText();
                String email = emailTextField.getText();

                StaffRecord staffRecord = new StaffRecord(id, lastName, firstName, mi, address, city, state, telephone, email);

                if (StaffValidator.isValidStaffRecord(staffRecord)) {
                    boolean success = staffDAO.updateStaff(staffRecord);
                    if (success) {
                        JOptionPane.showMessageDialog(Staff.this, "Successfully Updated record with ID: " + id, "Record Updated", JOptionPane.INFORMATION_MESSAGE);
                    }
                    clearTextFields();
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTextFields();
            }
        });

        buttonPanel.add(viewButton);
        buttonPanel.add(insertButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.CENTER);

        connectionStatusLabel = new JLabel("Database not connected");
        add(connectionStatusLabel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        updateConnectionStatusLabel();
    }

    private void clearTextFields() {
        idTextField.setText("");
        lastNameTextField.setText("");
        firstNameTextField.setText("");
        miTextField.setText("");
        addressTextField.setText("");
        cityTextField.setText("");
        stateTextField.setText("");
        telephoneTextField.setText("");
        emailTextField.setText("");
    }

    @Override
    public void dispose() {
        super.dispose();
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateConnectionStatusLabel() {
        if (connection != null) {
            connectionStatusLabel.setText("Database connected");
        } else {
            connectionStatusLabel.setText("Database not connected");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Staff());
    }
}