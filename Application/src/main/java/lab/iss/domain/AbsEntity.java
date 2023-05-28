package lab.iss.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbsEntity<T extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T ID;

    public AbsEntity(T ID) {
        this.ID = ID;
    }

    public T getID() {
        return ID;
    }

    public void setID(T ID) {
        this.ID = ID;
    }
}
