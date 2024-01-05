/*********************************
*  ITC – 5201 Database Programming Using Java – Assignment    4                                                 	         *

*  I declare that this assignment is my own work in accordance with Humber Academic Policy.        *

*  No part of this assignment has been copied manually or electronically from any other source       *

* (including web sites) or distributed to other students/social media.                                                        *
                                                                                                                                                                             
*  Name: Pelumi Owoshagba 	Student ID: N01574587 Date: 11/22/2023
*  Name: Chioma Kamalu 		Student ID: N01600998 Date: 11/22/2023
*  Name: Adekunle Omonihi  	Student ID: N01511618 Date: 11/22/2023
*****/
public class StaffRecord {
    private String id;
    private String lastName;
    private String firstName;
    private String mi;
    private String address;
    private String city;
    private String state;
    private String telephone;
    private String email;

    // Constructors
    public StaffRecord() {
        // Default constructor
    }

    public StaffRecord(String id, String lastName, String firstName, String mi, String address,
                       String city, String state, String telephone, String email) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.mi = mi;
        this.address = address;
        this.city = city;
        this.state = state;
        this.telephone = telephone;
        this.email = email;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMi() {
        return mi;
    }

    public void setMi(String mi) {
        this.mi = mi;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
