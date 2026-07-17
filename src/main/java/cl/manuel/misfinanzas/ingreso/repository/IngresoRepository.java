package cl.manuel.misfinanzas.ingreso.repository;

import cl.manuel.misfinanzas.ingreso.model.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Acceso a datos de ingresos. Spring Data genera la implementación en tiempo de ejecución.
 */
@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long> {

    /** Devuelve los ingresos activos (no eliminados lógicamente). */
    List<Ingreso> findByActivoTrue();
}
