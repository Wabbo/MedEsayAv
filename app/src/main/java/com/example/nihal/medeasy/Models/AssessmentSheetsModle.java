package com.example.nihal.medeasy.Models;

public class AssessmentSheetsModle {

    private String date, elt5ssos /*, description , drug*/;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getElt5ssos() {
        return elt5ssos;
    }

    public void setElt5ssos(String elt5ssos) {
        this.elt5ssos = elt5ssos;
    }

    public AssessmentSheetsModle(String date, String elt5ssos) {
        this.date = date;
        this.elt5ssos = elt5ssos;
    }

    public AssessmentSheetsModle() {
    }
}
