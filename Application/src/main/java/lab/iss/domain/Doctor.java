package lab.iss.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor extends Employee {

    @Column(name = "id_department")
    private int departmentID;

    public Doctor(int ID, String firstName, String lastName, String username, String password, int departmentID) {
        super(ID, firstName, lastName, username, password);
        this.departmentID = departmentID;
    }

    public Doctor() {
        super(1, "", "", "", "");
    }

    public int getDepartment() {
        return departmentID;
    }

    public void setDepartment(int departmentID) {
        this.departmentID = departmentID;
    }
}
