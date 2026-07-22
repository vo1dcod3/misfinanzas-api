package cl.manuel.misfinanzas.resumen.dto;

import cl.manuel.misfinanzas.gasto.model.CategoriaGasto;

/**
 * Total gastado (miCosto) en una categoría, para el desglose del resumen mensual.
 */
public class DesgloseCategoriaDTO {

    private CategoriaGasto categoria;
    private Long total;

    public DesgloseCategoriaDTO ( CategoriaGasto categoriaGasto, Long total) {
        this.categoria = categoriaGasto;
        this.total = total;
    }

    public CategoriaGasto getCategoria() { return categoria; }
    public Long getTotal () { return total; }

}
