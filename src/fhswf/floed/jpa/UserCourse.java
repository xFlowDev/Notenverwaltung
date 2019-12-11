package fhswf.floed.jpa;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_course", schema = "notenverwaltung", catalog = "")
public class UserCourse {
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "semester")
    private int semester;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCourse that = (UserCourse) o;
        return id == that.id &&
                semester == that.semester;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, semester);
    }
}
