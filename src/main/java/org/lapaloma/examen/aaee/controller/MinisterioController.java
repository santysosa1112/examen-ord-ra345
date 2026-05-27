/**
 * 
 */
package org.lapaloma.examen.aaee.controller;

import java.util.List;

import org.lapaloma.examen.aaee.service.MinisterioService;
import org.lapaloma.examen.aaee.vo.Ministerio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Isidoro Nevares Martín - Virgen de la Paloma Fecha creación: 13 mar 2026
 */
@RestController
@RequestMapping("/gobierno_ord/ministerios")
public class MinisterioController {
    private final MinisterioService ministerioService;

    // Spring inyecta automáticamente el service con su DAO
    public MinisterioController(MinisterioService ministerioService) {
        this.ministerioService = ministerioService;
    }

    // GET /gobierno_ord/ministerios - listar todos los ministerios
    @GetMapping
    public List<Ministerio> getAll() {
        List<Ministerio> listaMinisterios = ministerioService.obtenerListaMinisterios();
        return listaMinisterios;
    }

    // GET /gobierno_ord/ministerios/nombre/{nombre} - obtener un ministerio por nombre
    @GetMapping("/nombre/{nombre}")
    public Ministerio getByNombre(@PathVariable String nombre) {
        Ministerio ministerio = ministerioService.obtenerMinisterioPorNombre(nombre);
        return ministerio;
    }

    // GET /gobierno_ord/ministerios/id/{id} - obtener un ministerio por identificador
    @GetMapping("/id/{id}")
    public Ministerio getByIdentificador(@PathVariable int id) {
        Ministerio ministerio = ministerioService.obtenerMinisterioPorIdentificador(id);
        return ministerio;
    }

}
