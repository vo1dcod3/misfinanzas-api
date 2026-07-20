package cl.manuel.misfinanzas.gasto.model;

import jakarta.persistence.*;

/**
 * Gasto recurrente del usuario, con categoría fija y flag compartido (regla 50/50). Monto en CLP entero.
 */
@Entity
@Table(name = "gastos")
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Long monto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaGasto categoria;

    @Column(nullable = false)
    private boolean compartido = false;

    @Column(nullable = false)
    private boolean activo = true;

    public Gasto () {}

    public Gasto(String nombre, Long monto,CategoriaGasto categoria, boolean compartido ) {
        this.nombre = nombre;
        this.monto = monto;
        this.categoria = categoria;
        this.compartido = compartido;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Long getMonto() { return monto; }
    public void setMonto(Long monto) {this.monto = monto; }
    public CategoriaGasto getCategoria() { return categoria; }
    public void setCategoria(CategoriaGasto categoria) { this.categoria = categoria; }
    public boolean getCompartido() {return compartido; }
    public void setCompartido(boolean compartido) { this.compartido = compartido; }
    public boolean getActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }




}
