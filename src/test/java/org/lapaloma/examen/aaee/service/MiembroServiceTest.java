package org.lapaloma.examen.aaee.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lapaloma.examen.aaee.dao.IMiembroDAO;
import org.lapaloma.examen.aaee.excepcion.MiembroNoEncontradoException;
import org.lapaloma.examen.aaee.vo.Miembro;
import org.lapaloma.examen.aaee.vo.Ministerio;

class MiembroServiceTest {

    private MiembroService miembroService;
    private FakeMiembroDAO fakeDAO;

    @BeforeEach
    void setUp() {
        fakeDAO = new FakeMiembroDAO();
        miembroService = new MiembroService(fakeDAO);
    }

    // =========================
    // obtenerListaMiembros
    // =========================

    @Test
    void obtenerListaMiembros_cuandoListaEstaVacia_lanzaExcepcion() {
        assertThrows(RuntimeException.class, () -> {
            miembroService.obtenerListaMiembros();
        });
    }

    @Test
    void obtenerListaMiembros_cuandoHayDatos_retornaLista() {
        Ministerio ministerio = new Ministerio(1, "Ministerio de Defensa", 120000, 6000);
        Miembro miembro1 = new Miembro(1, "23451596F", "James", "Logan", "Lobezno", ministerio);

        fakeDAO.data.add(miembro1);

        List<Miembro> resultado = miembroService.obtenerListaMiembros();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("James", resultado.get(0).getNombre());
    }

    // =========================
    // obtenerMiembroPorNombre
    // =========================

    @Test
    void obtenerMiembroPorNombre_cuandoNoExiste_lanzaExcepcion() {
        assertThrows(MiembroNoEncontradoException.class, () -> {
            miembroService.obtenerMiembroPorNombre("NoExiste");
        });
    }

    @Test
    void obtenerMiembroPorNombre_cuandoExiste_retornaMiembro() {
        Ministerio ministerio = new Ministerio(1, "Ministerio de Defensa", 120000, 6000);
        Miembro miembro1 = new Miembro(1, "23451596F", "James", "Logan", "Lobezno", ministerio);

        fakeDAO.data.add(miembro1);

        Miembro resultado = miembroService.obtenerMiembroPorNombre("James");

        assertNotNull(resultado);
        assertEquals("James", resultado.getNombre());
        assertEquals("Logan", resultado.getApellido1());
        assertEquals("Lobezno", resultado.getAlias());
    }

    // =========================
    // obtenerMiembroPorAlias
    // =========================

    @Test
    void obtenerMiembroPorAlias_cuandoNoExiste_lanzaExcepcion() {
        assertThrows(MiembroNoEncontradoException.class, () -> {
            miembroService.obtenerMiembroPorAlias("AliasNoExiste");
        });
    }

    @Test
    void obtenerMiembroPorAlias_cuandoExiste_retornaMiembro() {
        Ministerio ministerio = new Ministerio(1, "Ministerio de Defensa", 120000, 6000);
        Miembro miembro1 = new Miembro(1, "23451596F", "James", "Logan", "Lobezno", ministerio);

        fakeDAO.data.add(miembro1);

        Miembro resultado = miembroService.obtenerMiembroPorAlias("Lobezno");

        assertNotNull(resultado);
        assertEquals("James", resultado.getNombre());
        assertEquals("Lobezno", resultado.getAlias());
    }

    @Test
    void obtenerMiembroPorAlias_cuandoAliasEsNull_manejaCorrectamente() {
        Ministerio ministerio = new Ministerio(2, "Ministerio de Economía", 375000, 38000);
        Miembro miembro1 = new Miembro(3, "36974641B", "Tío", "Gilito", null, ministerio);

        fakeDAO.data.add(miembro1);

        // Buscar un alias null debería lanzar excepción
        assertThrows(MiembroNoEncontradoException.class, () -> {
            miembroService.obtenerMiembroPorAlias(null);
        });
    }

    // =========================
    // FakeMiembroDAO
    // =========================

    static class FakeMiembroDAO implements IMiembroDAO {

        private List<Miembro> data = new ArrayList<>();

        @Override
        public List<Miembro> obtenerListaMiembros() {
            return new ArrayList<>(data);
        }

        @Override
        public Miembro obtenerMiembroPorNombre(String nombre) {
            return data.stream()
                    .filter(m -> m.getNombre().equals(nombre))
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public Miembro obtenerMiembroPorAlias(String alias) {
            return data.stream()
                    .filter(m -> {
                        String miembroAlias = m.getAlias();
                        return miembroAlias != null && miembroAlias.equals(alias);
                    })
                    .findFirst()
                    .orElse(null);
        }

    }

}
