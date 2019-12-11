package fhswf.floed.jpa;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "module_type", schema = "notenverwaltung", catalog = "")
@NamedQueries({
        @NamedQuery(name = "ModuleType.all", query = "SELECT mt FROM ModuleType mt")
})
public class ModuleType {
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
        ModuleType that = (ModuleType) o;
        return id == that.id &&
                name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
