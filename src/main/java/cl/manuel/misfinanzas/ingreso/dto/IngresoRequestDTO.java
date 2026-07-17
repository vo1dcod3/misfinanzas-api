package cl.manuel.misfinanzas.ingreso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Datos que el cliente envía para crear o actualizar un ingreso, con sus validaciones.
 */
public class IngresoRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El Monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a 0")
    private Long monto;

    public IngresoRequestDTO() {}

    public String getNombre() { return nombre; }
    public void  setNombre(String nombre) { this.nombre = nombre; }
    public Long getMonto() { return monto; }
    public void setMonto(Long monto) { this.monto = monto; }

}
