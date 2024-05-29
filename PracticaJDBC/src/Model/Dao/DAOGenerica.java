package Model.Dao;

public interface DAOGenerica<T> {
    boolean insert(T t);
    T llegir(T t);
    boolean update (T t);
    boolean delete(T t);
}
