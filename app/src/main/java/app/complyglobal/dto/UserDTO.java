package app.complyglobal.dto;

import java.io.Serializable;

/**
 * Created by skanala on 10/4/2017.
 */

public class UserDTO implements Serializable {

    private int userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private int mobilePin;
    private int isActive;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMobilePin() {
        return mobilePin;
    }

    public void setMobilePin(int mobilePin) {
        this.mobilePin = mobilePin;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", mobilePin=" + mobilePin +
                ", isActive=" + isActive +
                '}';
    }
}
