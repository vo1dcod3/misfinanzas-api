package cl.manuel.misfinanzas.ingreso.controller;

import cl.manuel.misfinanzas.ingreso.dto.IngresoRequestDTO;
import cl.manuel.misfinanzas.ingreso.dto.IngresoResponseDTO;
import cl.manuel.misfinanzas.ingreso.service.IngresoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints REST de ingresos, bajo /api/ingresos. Traduce las peticiones HTTP a llamadas al servicio.
 */
@RestController
@RequestMapping("/api/ingresos")
public class IngresoController {

    private final IngresoService ingresoService;

    public IngresoController(IngresoService ingresoService) {
        this.ingresoService = ingresoService;
    }

    /** GET /api/ingresos — lista los ingresos activos. */
    @GetMapping
    public ResponseEntity<List<IngresoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(ingresoService.obtenerTodos());
    }

    /** POST /api/ingresos — crea un ingreso y devuelve 201. */
    @PostMapping
    public ResponseEntity<IngresoResponseDTO> crear(@Valid @RequestBody IngresoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingresoService.crear(dto));
    }

    /** PUT /api/ingresos/{id} — actualiza un ingreso; 200 si existe, 404 si no. */
    @PutMapping("/{id}")
    public ResponseEntity<IngresoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody IngresoRequestDTO dto) {
        return ingresoService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** DELETE /api/ingresos/{id} — borrado lógico; 204 si existía, 404 si no. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (ingresoService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
