package fhswf.floed.jpa;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Lecturer {
    private int id;
    private String firstname;
    private String lastname;
    private String title;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "title")
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
}
