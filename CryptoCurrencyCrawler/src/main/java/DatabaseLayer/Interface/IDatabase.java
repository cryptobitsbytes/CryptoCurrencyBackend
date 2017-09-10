package DatabaseLayer.Interface;

import java.util.List;

/**
 * Created by Lennard on 10-9-2017.
 */
public interface IDatabase {
    void store(Object o);
    <T> List<T> findAll(Class<T> tClass);
}
