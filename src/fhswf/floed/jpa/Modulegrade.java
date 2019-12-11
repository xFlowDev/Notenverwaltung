package fhswf.floed.jpa;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Modulegrade {
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "grade")
    private double grade;
    @Basic
    @Column(name = "gradetry")
    private Integer gradetry;
    @Basic
    @Column(name = "creditpoints")
    private Integer creditpoints;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Integer getGradetry() {
        return gradetry;
    }

    public void setGradetry(Integer gradetry) {
        this.gradetry = gradetry;
    }

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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Module getModule() {
        return this.module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

}
