package dejavu.appzonegroup.com.dejavuandroid.Models;

import java.util.Date;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class UserDetailsModel {
    private String phoneNumber;
    private int gender;
    private String name;
    private String otherName;
    private String emailAddress;
    private String dob;

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDob() {
        return dob;
    }

    public int getGender() {
        return gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getOtherName() {
        return otherName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
