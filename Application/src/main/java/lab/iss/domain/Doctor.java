package lab.iss.domain;

public class Doctor extends Employee {

    private int departmentID;

    public Doctor(int ID, String firstName, String lastName, String username, String password, int departmentID) {
        super(ID, firstName, lastName, username, password);
        this.departmentID = departmentID;
    }

    public int getDepartment() {
        return departmentID;
    }

    public void setDepartment(int departmentID) {
        this.departmentID = departmentID;
    }
}
