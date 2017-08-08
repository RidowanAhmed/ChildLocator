package com.example.ridowanahmed.childlocator.HelperClass;

/**
 * Created by Ridowan Ahmed on 0007, August, 7, 2017.
 */

public class ParentInformation {
    private String parentName;
    private String parentMobile;
    private String parentEmail;
    private String parentPassword;

    public ParentInformation() {
    }

    public ParentInformation(String parentName, String parentMobile, String parentEmail, String parentPassword) {
        this.parentName = parentName;
        this.parentMobile = parentMobile;
        this.parentEmail = parentEmail;
        this.parentPassword = parentPassword;
    }

    public String getParentName() {
        return parentName;
    }

    public String getParentMobile() {
        return parentMobile;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public String getParentPassword() {
        return parentPassword;
    }
}
