
/*********************************
*  ITC – 5201 Database Programming Using Java – Assignment    4                                                 	         *

*  I declare that this assignment is my own work in accordance with Humber Academic Policy.        *

*  No part of this assignment has been copied manually or electronically from any other source       *

* (including web sites) or distributed to other students/social media.                                                        *
                                                                                                                                                                             
*  Name: Pelumi Owoshagba 	Student ID: N01574587 Date: 11/22/2023
*  Name: Chioma Kamalu 		Student ID: N01600998 Date: 11/22/2023
*  Name: Adekunle Omonihi  	Student ID: N01511618 Date: 11/22/2023
*****/
import javax.swing.JOptionPane;

public class StaffValidator {
    public static boolean isValidStaffRecord(StaffRecord staffRecord) {
        boolean isValid = isValidId(staffRecord.getId()) &&
                isValidName(staffRecord.getLastName(), "Last Name") &&
                isValidName(staffRecord.getFirstName(), "First Name") &&
                isValidMI(staffRecord.getMi()) &&
                isValidAddress(staffRecord.getAddress()) &&
                isValidState(staffRecord.getState()) &&
                isValidCity(staffRecord.getCity()) &&
                isValidTelephone(staffRecord.getTelephone()) &&
                isValidEmail(staffRecord.getEmail()); // Added email validation
        // Add additional validations for other fields if needed

        if (!isValid) {
            // Display a validation error message
            JOptionPane.showMessageDialog(null, "Invalid staff record. Please check the input.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        }

        return isValid;
    }

    private static boolean isValidId(String id) {
        // Implement ID validation logic (e.g., length, format, uniqueness)
        // Return true if valid, false otherwise
        return id != null && id.length() <= 9;
    }

    private static boolean isValidName(String name, String fieldName) {
        // Implement name validation logic (e.g., length, characters allowed)
        // Return true if valid, false otherwise
        return name != null && name.length() <= 15;
    }

    private static boolean isValidMI(String mi) {
        // Implement MI validation logic (e.g., length, format)
        // Return true if valid, false otherwise
        return mi != null && mi.length() == 1;
    }

    private static boolean isValidAddress(String address) {
        // Implement address validation logic (e.g., length)
        // Return true if valid, false otherwise
        return address != null && address.length() <= 20;
    }

    private static boolean isValidState(String state) {
        // Implement state validation logic (e.g., length)
        // Return true if valid, false otherwise
        return state != null && state.length() == 2;
    }

    private static boolean isValidCity(String city) {
        // Implement city validation logic (e.g., length)
        // Return true if valid, false otherwise
        return city != null && city.length() <= 20;
    }

    private static boolean isValidTelephone(String telephone) {
        // Implement telephone validation logic (e.g., length, format)
        // Return true if valid, false otherwise
        return telephone != null && telephone.length() == 10;
    }

    private static boolean isValidEmail(String email) {
        // Implement email validation logic (e.g., format)
        // Return true if valid, false otherwise
        return email != null && email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$");
    }
}
