package lab.iss.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class Department extends AbsEntity<Integer> {

    private String name;

    public Department(Integer ID, String name) {
        super(ID);
        this.name = name;
    }

    public Department() {
        super(1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
