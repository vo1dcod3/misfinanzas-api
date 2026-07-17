package cl.manuel.misfinanzas.ingreso.service;

import cl.manuel.misfinanzas.ingreso.dto.IngresoRequestDTO;
import cl.manuel.misfinanzas.ingreso.dto.IngresoResponseDTO;
import cl.manuel.misfinanzas.ingreso.model.Ingreso;
import cl.manuel.misfinanzas.ingreso.repository.IngresoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Lógica de negocio de ingresos: consultas, creación, actualización y borrado lógico.
 * Traduce entre la entidad {@link Ingreso} y sus DTOs para no exponer el modelo de datos hacia la API.
 */
@Service
public class IngresoService {

    private final IngresoRepository ingresoRepository;

    public IngresoService(IngresoRepository ingresoRepository) {
        this.ingresoRepository = ingresoRepository;
    }

    /** Devuelve todos los ingresos activos (no eliminados lógicamente). */
    public List<IngresoResponseDTO> obtenerTodos() {
        return ingresoRepository.findByActivoTrue()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /** Crea un ingreso nuevo y devuelve su DTO de respuesta. */
    public IngresoResponseDTO crear(IngresoRequestDTO dto) {
        Ingreso ingreso = new Ingreso(dto.getNombre(), dto.getMonto());
        return toResponseDTO(ingresoRepository.save(ingreso));
    }

    /** Actualiza un ingreso existente. Optional vacío si el id no existe. */
    public Optional<IngresoResponseDTO> actualizar(Long id, IngresoRequestDTO dto) {
        return ingresoRepository.findById(id)
                .map(ingreso -> {
                    ingreso.setNombre(dto.getNombre());
                    ingreso.setMonto(dto.getMonto());
                    return toResponseDTO(ingresoRepository.save(ingreso));
                });
    }

    /**
     * Borrado lógico: marca el ingreso como inactivo en vez de eliminarlo de la base.
     * @return true si el ingreso existía y se desactivó; false si no existía.
     */
    public boolean eliminar(Long id) {
        return ingresoRepository.findById(id)
                .map(ingreso -> {
                    ingreso.setActivo(false);
                    ingresoRepository.save(ingreso);
                    return true;
                })
                .orElse(false);
    }

    /** Convierte una entidad Ingreso en su DTO de respuesta. */
    private IngresoResponseDTO toResponseDTO(Ingreso ingreso) {
        return new IngresoResponseDTO(
                ingreso.getId(),
                ingreso.getNombre(),
                ingreso.getMonto()
        );
    }
}
