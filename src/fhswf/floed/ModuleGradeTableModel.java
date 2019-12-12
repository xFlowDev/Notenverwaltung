package fhswf.floed;

import fhswf.floed.jpa.Modulegrade;

public class ModuleGradeTableModel {
    private int id;
    private String name;
    private double grade;
    private int creditpoints;
    private int gradetry;

    public ModuleGradeTableModel(Modulegrade modulegrade) {
        this.id = modulegrade.getId();
        this.name = modulegrade.getModule().getName();
        this.grade = modulegrade.getGrade();
        this.creditpoints = modulegrade.getCreditpoints();
        this.gradetry = modulegrade.getGradetry();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getCreditpoints() {
        return creditpoints;
    }

    public void setCreditpoints(int creditpoints) {
        this.creditpoints = creditpoints;
    }

    public int getGradetry() {
        return gradetry;
    }

    public void setGradetry(int gradetry) {
        this.gradetry = gradetry;
    }
}
