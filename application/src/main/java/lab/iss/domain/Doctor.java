package lab.iss.domain;

public class Doctor extends Employee {

    private String department;

    public Doctor(int ID, String firstName, String lastName, String username, String password, String department) {
        super(ID, firstName, lastName, username, password);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
