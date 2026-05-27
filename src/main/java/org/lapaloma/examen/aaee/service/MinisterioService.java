/**
 * 
 */
package org.lapaloma.examen.aaee.service;

import java.util.List;

import org.lapaloma.examen.aaee.dao.IMinisterioDAO;
import org.lapaloma.examen.aaee.excepcion.MinisterioNoEncontradoException;
import org.lapaloma.examen.aaee.vo.Ministerio;
import org.springframework.stereotype.Service;

@Service
public class MinisterioService {

    private final IMinisterioDAO ministerioDAO;

    // Spring inyecta el DAO automáticamente
    public MinisterioService(IMinisterioDAO ministerioDAO) {
        this.ministerioDAO = ministerioDAO;
    }
    
    public List<Ministerio> obtenerListaMinisterios() {

        List<Ministerio> lista = ministerioDAO.obtenerListaMinisterios();

        // Simulamos el caso de lista vacía para probar la excepción
        lista=null;

        if (lista == null || lista.isEmpty()) {
            throw new RuntimeException("No hay ministerios disponibles");
        }

        return lista;
    }

    public Ministerio obtenerMinisterioPorNombre(String nombre) {
        Ministerio ministerio = ministerioDAO.obtenerMinisterioPorNombre(nombre);

        if (ministerio == null) {
            throw new MinisterioNoEncontradoException("Ministerio con nombre '" + nombre + "' no encontrado");
        }

        return ministerio;
    }

    public Ministerio obtenerMinisterioPorIdentificador(int identificador) {
        Ministerio ministerio = ministerioDAO.obtenerMinisterioPorIdentificador(identificador);

        // Simulamos el caso de lista vacía para probar la excepción
        ministerio=null;

        if (ministerio == null) {
            throw new MinisterioNoEncontradoException("Ministerio con identificador '" + identificador + "' no encontrado");
        }

        return ministerio;
    }

}