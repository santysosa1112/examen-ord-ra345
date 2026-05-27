package org.lapaloma.examen.aaee.dao;

import java.util.List;

import org.lapaloma.examen.aaee.vo.Ministerio;

public interface IMinisterioDAO {

    public List<Ministerio> obtenerListaMinisterios();

    public Ministerio obtenerMinisterioPorNombre(String nombre);

    public Ministerio obtenerMinisterioPorIdentificador(int identificador);
}
