package fhswf.floed.jpa;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Type.all", query = "SELECT t FROM Type t"),
        @NamedQuery(name = "Type.findByName", query = "SELECT t FROM Type t where t.name = :name")
})
public class Type {
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return id == type.id &&
                Objects.equals(name, type.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
