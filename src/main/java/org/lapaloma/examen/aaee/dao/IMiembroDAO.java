package org.lapaloma.examen.aaee.dao;

import java.util.List;

import org.lapaloma.examen.aaee.vo.Miembro;

public interface IMiembroDAO {

    public List<Miembro> obtenerListaMiembros();
    
    public Miembro obtenerMiembroPorNombre(String nombre);
    
    public Miembro obtenerMiembroPorAlias(String alias);
}
