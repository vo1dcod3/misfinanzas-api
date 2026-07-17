package cl.manuel.misfinanzas.ingreso.model;

import jakarta.persistence.*;

/**
 * Ingreso recurrente del usuario (sueldo u otra entrada). El monto es CLP entero (sin decimales).
 */
@Entity
@Table(name = "ingresos")
public class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Long monto;

    @Column(nullable = false)
    private boolean activo = true;

    public Ingreso() {}

    public Ingreso(String nombre, Long monto) {
        this.nombre = nombre;
        this.monto = monto;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Long getMonto() { return monto; }
    public void setMonto(Long monto) { this.monto = monto; }
    public boolean getActivo() { return  activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
