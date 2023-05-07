package lab.iss.domain;

public class Department extends Entity<Integer> {

    private String name;

    public Department(Integer ID, String name) {
        super(ID);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
