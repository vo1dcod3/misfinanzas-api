package cl.manuel.misfinanzas.gasto.dto;

/**
 * Estado resultante de un gasto en un mes que la API devuelve tras marcarlo.
 */
public class EstadoMensualResponseDTO {

    private Long gastoId;
    private String mes;
    private boolean pagado;

    public EstadoMensualResponseDTO (Long gastoId, String mes, boolean pagado) {
        this.gastoId = gastoId;
        this.mes = mes;
        this.pagado = pagado;
    }

    public Long getGastoId () { return gastoId; }
    public String getMes () { return mes; }
    public boolean isPagado() { return pagado; }

}
