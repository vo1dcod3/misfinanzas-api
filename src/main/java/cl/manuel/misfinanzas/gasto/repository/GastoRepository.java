package cl.manuel.misfinanzas.gasto.repository;

import cl.manuel.misfinanzas.gasto.model.CategoriaGasto;
import cl.manuel.misfinanzas.gasto.model.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Acceso a datos de gastos. Spring Data genera la implementación en tiempo de ejecución.
 */
@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {

    /** Devuelve los gastos activos (no eliminados lógicamente). */
    List<Gasto> findByActivoTrue();

    /** Devuelve los gastos activos de una categoría. */
    List<Gasto> findByCategoriaAndActivoTrue(CategoriaGasto categoria);

}
