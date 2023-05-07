package lab.iss.domain;

public class Pharmacy extends Entity<Integer> {

    private String name;

    public Pharmacy(Integer ID, String name) {
        super(ID);
        this.name = name;
    }
}
