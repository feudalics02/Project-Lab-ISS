package lab.iss.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pharmacists")
public class Pharmacist extends Employee {

    @Column(name = "id_pharmacy")
    private int pharmacyID;

    public Pharmacist(int ID, String firstName, String lastName, String username, String password, int pharmacyID) {
        super(ID, firstName, lastName, username, password);
        this.pharmacyID = pharmacyID;
    }

    public Pharmacist() {
        super(1, "", "", "", "");
    }

    public int getPharmacy() {
        return pharmacyID;
    }

    public void setPharmacy(int pharmacyID) {
        this.pharmacyID = pharmacyID;
    }
}
