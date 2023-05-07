package lab.iss.domain;

public class Pharmacist extends Employee {

    private int pharmacyID;

    public Pharmacist(int ID, String firstName, String lastName, String username, String password, int pharmacyID) {
        super(ID, firstName, lastName, username, password);
        this.pharmacyID = pharmacyID;
    }

    public int getPharmacy() {
        return pharmacyID;
    }

    public void setPharmacy(int pharmacyID) {
        this.pharmacyID = pharmacyID;
    }
}
