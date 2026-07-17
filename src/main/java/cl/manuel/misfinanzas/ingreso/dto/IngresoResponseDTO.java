package cl.manuel.misfinanzas.ingreso.dto;

/**
 * Datos de un ingreso que la API devuelve al cliente (sin exponer campos internos como activo).
 */
public class IngresoResponseDTO {

    private Long id;
    private String nombre;
    private Long monto;

    public IngresoResponseDTO(Long id, String nombre, Long monto) {
        this.id = id;
        this.nombre = nombre;
        this.monto = monto;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public Long getMonto() {return monto; }

}
