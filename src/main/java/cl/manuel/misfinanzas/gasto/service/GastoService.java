package cl.manuel.misfinanzas.gasto.service;

import cl.manuel.misfinanzas.gasto.dto.EstadoMensualRequestDTO;
import cl.manuel.misfinanzas.gasto.dto.EstadoMensualResponseDTO;
import cl.manuel.misfinanzas.gasto.dto.GastoRequestDTO;
import cl.manuel.misfinanzas.gasto.dto.GastoResponseDTO;
import cl.manuel.misfinanzas.gasto.model.CategoriaGasto;
import cl.manuel.misfinanzas.gasto.model.EstadoMensualGasto;
import cl.manuel.misfinanzas.gasto.model.Gasto;
import cl.manuel.misfinanzas.gasto.repository.EstadoMensualGastoRepository;
import cl.manuel.misfinanzas.gasto.repository.GastoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Lógica de negocio de gastos: consultas, creación, actualización y borrado lógico.
 * Aplica la regla 50/50 (miCosto) al convertir a DTO, para no exponer el modelo de datos hacia la API.
 */
@Service
public class GastoService {

    private final GastoRepository gastoRepository;
    private final EstadoMensualGastoRepository estadoMensualGastoRepository;

    public GastoService(GastoRepository gastoRepository, EstadoMensualGastoRepository estadoMensualGastoRepository) {
        this.gastoRepository = gastoRepository;
        this.estadoMensualGastoRepository = estadoMensualGastoRepository;
    }

    /** Devuelve todos los gastos activos (no eliminados lógicamente). */
    public List<GastoResponseDTO> obtenerTodos() {
        return gastoRepository.findByActivoTrue()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /** Devuelve los gastos activos de una categoría. */
    public List<GastoResponseDTO> obtenerPorCategoria(CategoriaGasto categoria) {
        return gastoRepository.findByCategoriaAndActivoTrue(categoria)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /** Crea un gasto nuevo y devuelve su DTO de respuesta (con miCosto calculado). */
    public GastoResponseDTO crear(GastoRequestDTO dto) {
        Gasto gasto = new Gasto(dto.getNombre(), dto.getMonto(), dto.getCategoria(), dto.getCompartido());
        return toResponseDTO(gastoRepository.save(gasto));
    }

    /** Actualiza un gasto existente. Optional vacío si el id no existe. */
    public Optional<GastoResponseDTO> actualizar(Long id, GastoRequestDTO dto) {
        return gastoRepository.findById(id)
                .map(gasto -> {
                    gasto.setNombre(dto.getNombre());
                    gasto.setMonto(dto.getMonto());
                    gasto.setCategoria(dto.getCategoria());
                    gasto.setCompartido(dto.getCompartido());
                    return toResponseDTO(gastoRepository.save(gasto));
                });
    }

    /**
     * Borrado lógico: marca el gasto como inactivo en vez de eliminarlo de la base.
     * @return true si el gasto existía y se desactivó; false si no existía.
     */
    public boolean eliminar(Long id) {
        return gastoRepository.findById(id)
                .map(gasto -> {
                    gasto.setActivo(false);
                    gastoRepository.save(gasto);
                    return true;
                })
                .orElse(false);
    }

    /**
     * Convierte un Gasto en su DTO de respuesta. Calcula miCosto según la regla 50/50:
     * la mitad del monto si el gasto es compartido, el total si no lo es.
     */
    private GastoResponseDTO toResponseDTO(Gasto gasto) {
        return new GastoResponseDTO(
                gasto.getId(),
                gasto.getNombre(),
                gasto.getMonto(),
                gasto.getCategoria(),
                gasto.getCompartido(),
                gasto.getMiCosto()      // ← ahora usa el método de la entidad
        );
    }

    /**
     * Marca el estado (pagado/pendiente) de un gasto en un mes. Actualiza el estado si ya existía
     * o lo crea si es la primera vez (upsert). Optional vacío si el gasto no existe.
     */
    public Optional<EstadoMensualResponseDTO> marcarEstado(Long gastoId, EstadoMensualRequestDTO dto) {
        return gastoRepository.findById(gastoId).map(gasto -> {
            EstadoMensualGasto estado = estadoMensualGastoRepository
                    .findByGasto_IdAndMes(gastoId, dto.getMes())
                    .orElseGet(() -> new EstadoMensualGasto(gasto, dto.getMes(),dto.isPagado()));

            estado.setPagado(dto.isPagado());
            estadoMensualGastoRepository.save(estado);
            return new EstadoMensualResponseDTO(gastoId, dto.getMes(), dto.isPagado());
        });
    }


}
