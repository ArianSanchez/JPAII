package Dao;

import java.util.List;

public interface IGenerica<T> {

    void registrar(T modelo) throws Exception;

    void editar(T modelo) throws Exception;

    void eliminar(T modelo) throws Exception;

    List<T> listar() throws Exception;
}
