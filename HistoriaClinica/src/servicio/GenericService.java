package servicio;

import java.util.List;

public interface GenericService<T> {
    void insertar(T t);
    void actualizar(T t);
    void eliminar(Long id);
    void recuperar(Long id);
    T getById(Long id);
    List<T> getAll();

}
