package cl.manuel.misfinanzas.gasto.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Datos que el cliente envía para marcar el estado de un gasto en un mes: el mes y si está pagado.
 */
public class EstadoMensualRequestDTO {

    @NotBlank(message = "El Mes es obligatorio")
    private String mes;

    private boolean pagado;

    public EstadoMensualRequestDTO () {}

    public String getMes() { return mes; }
    public void setMes(String mes) { this.mes = mes; }
    public boolean isPagado() { return pagado; }
    public void setPagado( boolean pagado ) { this.pagado = pagado;}

}
