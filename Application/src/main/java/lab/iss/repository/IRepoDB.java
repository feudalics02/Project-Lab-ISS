package lab.iss.repository;

import java.util.List;

public interface IRepoDB<T, ID> {

    int add(T t);

    void remove(ID id);

    List<T> getAll();

}
