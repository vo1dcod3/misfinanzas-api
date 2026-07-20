package cl.manuel.misfinanzas.gasto.dto;

import cl.manuel.misfinanzas.gasto.model.CategoriaGasto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Datos que el cliente envía para crear o actualizar un gasto, con sus validaciones.
 */
public class GastoRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a 0")
    private Long monto;

    @NotNull(message = "La categoria es Obligatoria")
    private CategoriaGasto categoria;

    private boolean compartido = false;

    public GastoRequestDTO () {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Long getMonto() { return monto; }
    public void setMonto( Long monto) { this.monto = monto; }
    public CategoriaGasto getCategoria () { return categoria; }
    public void  setCategoria( CategoriaGasto categoria ) { this.categoria = categoria; }
    public boolean getCompartido() { return compartido; }
    public void setCompartido(boolean compartido) { this.compartido = compartido; }



}
