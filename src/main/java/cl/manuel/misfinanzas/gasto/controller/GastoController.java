package cl.manuel.misfinanzas.gasto.controller;

import cl.manuel.misfinanzas.gasto.dto.EstadoMensualRequestDTO;
import cl.manuel.misfinanzas.gasto.dto.EstadoMensualResponseDTO;
import cl.manuel.misfinanzas.gasto.dto.GastoRequestDTO;
import cl.manuel.misfinanzas.gasto.dto.GastoResponseDTO;
import cl.manuel.misfinanzas.gasto.model.CategoriaGasto;
import cl.manuel.misfinanzas.gasto.service.GastoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints REST de gastos, bajo /api/gastos. Traduce las peticiones HTTP a llamadas al servicio.
 */
@RestController
@RequestMapping("/api/gastos")
public class GastoController {

    private final GastoService gastoService;

    public GastoController(GastoService gastoService) {
        this.gastoService = gastoService;
    }

    /** GET /api/gastos[?categoria=] — lista los gastos activos, o filtra por categoría si se indica. */
    @GetMapping
    public ResponseEntity<List<GastoResponseDTO>> obtener(@RequestParam(required = false) CategoriaGasto categoria) {
        if (categoria == null) {
            return ResponseEntity.ok(gastoService.obtenerTodos());
        }
        return ResponseEntity.ok(gastoService.obtenerPorCategoria(categoria));
    }

    /** POST /api/gastos — crea un gasto y devuelve 201. */
    @PostMapping
    public ResponseEntity<GastoResponseDTO> crear(@Valid @RequestBody GastoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gastoService.crear(dto));
    }

    /** PUT /api/gastos/{id} — actualiza un gasto; 200 si existe, 404 si no. */
    @PutMapping("/{id}")
    public ResponseEntity<GastoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody GastoRequestDTO dto) {
        return gastoService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** DELETE /api/gastos/{id} — borrado lógico; 204 si existía, 404 si no. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (gastoService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<EstadoMensualResponseDTO> marcarEstado(@PathVariable Long id, @Valid @RequestBody EstadoMensualRequestDTO dto) {
        return gastoService.marcarEstado(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
