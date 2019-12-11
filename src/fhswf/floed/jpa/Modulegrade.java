package fhswf.floed.jpa;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Modulegrade {
    private int id;
    private double grade;
    private Integer gradetry;
    private Integer creditpoints;
    private User user;
    private Module module;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "grade")
    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "gradetry")
    public Integer getGradetry() {
        return gradetry;
    }

    public void setGradetry(Integer gradetry) {
        this.gradetry = gradetry;
    }

    @Basic
    @Column(name = "creditpoints")
    public Integer getCreditpoints() {
        return creditpoints;
    }

    public void setCreditpoints(Integer creditpoints) {
        this.creditpoints = creditpoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modulegrade that = (Modulegrade) o;
        return id == that.id &&
                Double.compare(that.grade, grade) == 0 &&
                Objects.equals(gradetry, that.gradetry) &&
                Objects.equals(creditpoints, that.creditpoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grade, gradetry, creditpoints);
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "module_id")
    public Module getModule() {
        return this.module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

}
