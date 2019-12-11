package fhswf.floed.jpa;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.all", query = "SELECT u FROM User u"),
        @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.username = :name")
})
public class User {
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
    @Column(name = "email")
    private String email;
    @OneToOne
    @JoinColumn(name = "type_id")
    private Type userType;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "salt")
    private String salt;
    @Basic
    @Column(name = "username")
    private String username;
    @OneToMany(mappedBy = "user")
    private List<Modulegrade> grades;

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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(lastname, user.lastname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(salt, user.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, email, password, salt);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Type getType() {
        return this.userType;
    }

    public void setType(Type type) {
        this.userType = type;
    }

    public List<Modulegrade> getGrades() {
        return this.grades;
    }

    public void setGrades(List<Modulegrade> grades) {
        this.grades = grades;
    }


}
