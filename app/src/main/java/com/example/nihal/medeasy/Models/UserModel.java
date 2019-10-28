package com.example.nihal.medeasy.Models;

public class UserModel {

    private String UserName, YearOfBirth, Address, Occupation, FamilyHistoryLink,
            Weight, Height, Password, PhoneNumber, type, gender, userID,
            autoPassword, bloodType, status;

    public UserModel(String userName, String yearOfBirth, String address, String occupation, String familyHistoryLink, String weight, String height, String password, String phoneNumber, String type, String gender, String userID, String autoPassword, String bloodType, String status) {
        UserName = userName;
        YearOfBirth = yearOfBirth;
        Address = address;
        Occupation = occupation;
        FamilyHistoryLink = familyHistoryLink;
        Weight = weight;
        Height = height;
        Password = password;
        PhoneNumber = phoneNumber;
        this.type = type;
        this.gender = gender;
        this.userID = userID;
        this.autoPassword = autoPassword;
        this.bloodType = bloodType;
        this.status = status;
    }
    public UserModel() {
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getYearOfBirth() {
        return YearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        YearOfBirth = yearOfBirth;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public String getFamilyHistoryLink() {
        return FamilyHistoryLink;
    }

    public void setFamilyHistoryLink(String familyHistoryLink) {
        FamilyHistoryLink = familyHistoryLink;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAutoPassword() {
        return autoPassword;
    }

    public void setAutoPassword(String autoPassword) {
        this.autoPassword = autoPassword;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}

