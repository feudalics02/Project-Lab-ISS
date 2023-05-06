package lab.iss.domain;

public class Medicine extends Entity<Integer> {

    private String name;

    private String description;

    public Medicine(Integer ID, String name, String description) {
        super(ID);
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
