package lab.iss.domain;

public abstract class Entity<T> {

    private T ID;

    public Entity(T ID) {
        this.ID = ID;
    }

    public T getID() {
        return ID;
    }
}
