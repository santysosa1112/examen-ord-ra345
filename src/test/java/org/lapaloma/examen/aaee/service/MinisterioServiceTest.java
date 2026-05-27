package org.lapaloma.examen.aaee.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lapaloma.examen.aaee.dao.IMinisterioDAO;
import org.lapaloma.examen.aaee.excepcion.MinisterioNoEncontradoException;
import org.lapaloma.examen.aaee.service.MinisterioService;
import org.lapaloma.examen.aaee.vo.Ministerio;

class MinisterioServiceTest {

    private MinisterioService ministerioService;
    private FakeMinisterioDAO fakeDAO;

    @BeforeEach
    void setUp() {
        fakeDAO = new FakeMinisterioDAO();
        ministerioService = new MinisterioService(fakeDAO);
    }

    // =========================
    // obtenerListaMinisterios
    // =========================

    @Test
    void obtenerListaMinisterios_cuandoListaEstaVacio_lanzaExcepcion() {
        assertThrows(RuntimeException.class, () -> {
            ministerioService.obtenerListaMinisterios();
        });
    }

    @Test
    void obtenerListaMinisterio_cuandoHayDatos_retornaLista() {
        fakeDAO.data.add(new Ministerio(1, "Ministerio de Defensa", 120000, 100000));

        List<Ministerio> resultado = ministerioService.obtenerListaMinisterios();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    // =========================
    // obtenerMinisterioPorNombre
    // =========================

    @Test
    void obtenerMinisterioPorNombre_cuandoNoExiste_lanzaExcepcion() {
        assertThrows(MinisterioNoEncontradoException.class, () -> {
            ministerioService.obtenerMinisterioPorNombre("NoExiste");
        });
    }

    @Test
    void obtenerMinisterioPorNombre_cuandoExiste_retornaMinisterio() {
        fakeDAO.data.add(new Ministerio(1, "Ministerio de Defensa", 120000, 100000));

        Ministerio resultado = ministerioService.obtenerMinisterioPorNombre("Ministerio de Defensa");

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdentificador());
        assertEquals("Ministerio de Defensa", resultado.getNombre());
    }

    // =========================
    // obtenerMinisterioPorIdentificador
    // =========================

    @Test
    void obtenerMinisterioPorIdentificador_cuandoNoExiste_lanzaExcepcion() {
        assertThrows(MinisterioNoEncontradoException.class, () -> {
            ministerioService.obtenerMinisterioPorIdentificador(99);
        });
    }

    @Test
    void obtenerMinisterioPorIdentificador_cuandoExiste_retornaMinisterio() {
        fakeDAO.data.add(new Ministerio(2, "Ministerio de Economía", 375000, 38000));

        Ministerio resultado = ministerioService.obtenerMinisterioPorIdentificador(2);

        assertNotNull(resultado);
        assertEquals(2, resultado.getIdentificador());
        assertEquals("Ministerio de Economía", resultado.getNombre());
    }
    
    static class FakeMinisterioDAO implements IMinisterioDAO {

        private List<Ministerio> data = new ArrayList<>();

        @Override
        public List<Ministerio> obtenerListaMinisterios() {
            return new ArrayList<>(data);
        }

        @Override
        public Ministerio obtenerMinisterioPorNombre(String nombre) {
            return data.stream()
                    .filter(m -> m.getNombre().equals(nombre))
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public Ministerio obtenerMinisterioPorIdentificador(int identificador) {
            return data.stream()
                    .filter(m -> m.getIdentificador() == identificador)
                    .findFirst()
                    .orElse(null);
        }

    }

}