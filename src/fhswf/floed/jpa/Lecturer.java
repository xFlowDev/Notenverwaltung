package fhswf.floed.jpa;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "lecturer")
@NamedQueries({
        @NamedQuery(name = "Lecturer.all", query = "SELECT l FROM lecturer l")
})
public class Lecturer {
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "firstname")
    private String firstname;
    @Basic
    @Column(name = "lastname")
    private String lastname;
    @Basic
    @Column(name = "title")
    private String title;
    @OneToMany(mappedBy = "lecturer")
    private List<Module> modules;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecturer lecturer = (Lecturer) o;
        return id == lecturer.id &&
                Objects.equals(firstname, lecturer.firstname) &&
                Objects.equals(lastname, lecturer.lastname) &&
                Objects.equals(title, lecturer.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, title);
    }

    public String fullName() {
        return this.title + " " + this.firstname + " " + this.lastname;
    }

    public List<Module> getModules() {
        return this.modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
