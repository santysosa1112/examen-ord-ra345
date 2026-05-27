package org.lapaloma.examen.aaee.controller;

import java.util.List;

import org.lapaloma.examen.aaee.service.MiembroService;
import org.lapaloma.examen.aaee.vo.Miembro;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para gestionar miembros de ministerios
 * 
 * @author Isidoro Nevares Martín - IES Virgen de la Paloma
 * @date 08 mayo 2026
 */
@RestController
@RequestMapping("/gobierno_ord/miembros")
public class MiembroController {
    private final MiembroService miembroService;

    // Spring inyecta automáticamente el service con su DAO
    public MiembroController(MiembroService miembroService) {
        this.miembroService = miembroService;
    }

    /**
     * GET /gobierno_ord/miembros - Obtiene la lista de todos los miembros
     * 
     * @return List<Miembro> lista de todos los miembros en formato JSON
     */
    @GetMapping
    public List<Miembro> getAll() {
        List<Miembro> listaMiembros = miembroService.obtenerListaMiembros();
        return listaMiembros;
    }

    /**
     * GET /gobierno_ord/miembros/nombre/{nombre} - Obtiene un miembro por su nombre
     * 
     * @param nombre el nombre del miembro a buscar
     * @return Miembro el miembro encontrado en formato JSON
     */
    @GetMapping("/nombre/{nombre}")
    public Miembro getByNombre(@PathVariable String nombre) {
        Miembro miembro = miembroService.obtenerMiembroPorNombre(nombre);
        return miembro;
    }

    /**
     * GET /gobierno_ord/miembros/alias/{alias} - Obtiene un miembro por su alias
     * 
     * @param alias el alias del miembro a buscar
     * @return Miembro el miembro encontrado en formato JSON
     */
    @GetMapping("/alias/{alias}")
    public Miembro getByAlias(@PathVariable String alias) {
        Miembro miembro = miembroService.obtenerMiembroPorAlias(alias);
        return miembro;
    }

}
