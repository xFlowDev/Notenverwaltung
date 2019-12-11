package fhswf.floed.jpa;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Module.all", query = "SELECT m FROM Module m")
})
public class Module {
    @Id
    @Column(name = "id")
    private int id;
    private String name;
    private int creditpoints;
    private int weekhours;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    private List<Modulegrade> grades;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        return id == module.id &&
                Objects.equals(name, module.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Basic
    @Column(name = "creditpoints")
    public int getCreditpoints() {
        return creditpoints;
    }

    public void setCreditpoints(int creditpoints) {
        this.creditpoints = creditpoints;
    }

    @Basic
    @Column(name = "weekhours")
    public int getWeekhours() {
        return weekhours;
    }

    public void setWeekhours(int weekhours) {
        this.weekhours = weekhours;
    }


    public Lecturer getLecturer() {
        return this.lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @OneToMany(mappedBy = "module")
    public List<Modulegrade> getGrades() {
        return this.grades;
    }

    public void setGrades(List<Modulegrade> grades) {
        this.grades = grades;
    }

}
