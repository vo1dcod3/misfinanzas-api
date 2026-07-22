package cl.manuel.misfinanzas.resumen.dto;

import java.util.List;

/**
 * Resumen financiero calculado de un mes: totales de ingresos y gastos, reembolso, ahorro,
 * pagado/pendiente y el desglose por categoría. Es la respuesta que alimenta el dashboard.
 */
public class ResumenResponseDTO {

    private String mes;
    private Long ingresoTotal;
    private Long gastosPropios;
    private Long reembolso;
    private Long ahorro;
    private Long pagado;
    private Long pendiente;
    private List<DesgloseCategoriaDTO> desglosePorCategoria;

    public ResumenResponseDTO (String mes, Long ingresoTotal, Long gastosPropios, Long reembolso, Long ahorro, Long pagado, Long pendiente, List<DesgloseCategoriaDTO> desgloseCategoria) {
        this.mes = mes;
        this.ingresoTotal = ingresoTotal;
        this.gastosPropios = gastosPropios;
        this.reembolso = reembolso;
        this.ahorro = ahorro;
        this.pagado = pagado;
        this.pendiente = pendiente;
        this.desglosePorCategoria = desgloseCategoria;

    }

    public String getMes () {return mes; }
    public Long getIngresoTotal () { return  ingresoTotal; }
    public Long getGastosPropios () { return  gastosPropios; }
    public Long getReembolso () { return reembolso; }
    public Long getAhorro () {return  ahorro; }
    public Long getPagado () { return  pagado; }
    public Long getPendiente () { return pendiente; }
    public List<DesgloseCategoriaDTO> getDesgloseCategoria () { return desglosePorCategoria;}
}
