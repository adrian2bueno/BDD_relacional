package Model.DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAOGenerica<T,ID> {
    boolean insert(T t);
    boolean update(T t);
    boolean delete(ID id);
    T findbyId(ID id);
    List<T> findAll();
    int count() throws SQLException;
}