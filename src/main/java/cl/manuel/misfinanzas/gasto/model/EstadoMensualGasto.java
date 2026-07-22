package cl.manuel.misfinanzas.gasto.model;

import jakarta.persistence.*;

/**
 * Estado pagado/pendiente de un gasto en un mes concreto: una fila por (gasto, mes).
 * La definición del gasto es recurrente, pero su estado varía cada mes.
 */
@Entity
@Table(
        name ="estados_mensuales_gasto",
        uniqueConstraints = @UniqueConstraint(columnNames = {"gasto_id", "mes"})
)
public class EstadoMensualGasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gasto_id", nullable = false)
    private Gasto gasto;

    @Column(nullable = false)
    private String mes;

    @Column(nullable = false)
    private boolean pagado;

    public EstadoMensualGasto() {}

    public EstadoMensualGasto (Gasto gasto, String mes, boolean pagado) {
        this.gasto = gasto;
        this.mes = mes;
        this.pagado = pagado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Gasto getGasto() { return gasto; }
    public void setGasto(Gasto gasto) { this.gasto = gasto; }
    public String getMes() { return mes; }
    public void setMes(String mes) { this.mes = mes; }
    public boolean isPagado() { return pagado; }
    public void setPagado(boolean pagado) { this.pagado = pagado; }
}
