package cl.manuel.misfinanzas.gasto.dto;

import cl.manuel.misfinanzas.gasto.model.CategoriaGasto;

/**
 * Datos de un gasto que la API devuelve, incluido miCosto (la mitad si es compartido, el total si no).
 */
public class GastoResponseDTO {

    private Long id;
    private String nombre;
    private Long monto;
    private CategoriaGasto categoria;
    private boolean compartido;
    private Long miCosto;

    public GastoResponseDTO (Long id, String nombre, Long monto, CategoriaGasto categoria, boolean compartido, Long miCosto) {

        this.id = id;
        this.nombre = nombre;
        this.monto = monto;
        this.categoria = categoria;
        this.compartido = compartido;
        this.miCosto = miCosto;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public Long getMonto() { return monto; }
    public CategoriaGasto getCategoria() { return categoria;}
    public boolean getCompartido() { return compartido; }
    public Long getMiCosto() { return miCosto; }
}
