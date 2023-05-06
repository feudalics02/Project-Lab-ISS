package lab.iss.repository;

import java.util.List;

public interface IRepoDB<T, ID> {

    void add(T t);

    void remove(ID id);

    List<T> getAll();

}
