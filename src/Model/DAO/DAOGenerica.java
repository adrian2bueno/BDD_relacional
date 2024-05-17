package Model.DAO;

import java.util.List;

public interface DAOGenerica<T,ID> {
    T findbyId(ID id);
    boolean insert(T t);
    boolean update(T t);
    boolean delete(ID id);
    List<T> findAll();
}