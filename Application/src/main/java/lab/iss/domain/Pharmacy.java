package lab.iss.domain;

public class Pharmacy extends AbsEntity<Integer> {

    private String name;

    public Pharmacy(Integer ID, String name) {
        super(ID);
        this.name = name;
    }
}
