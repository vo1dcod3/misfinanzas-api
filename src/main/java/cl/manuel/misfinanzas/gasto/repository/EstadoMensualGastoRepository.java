package cl.manuel.misfinanzas.gasto.repository;

import cl.manuel.misfinanzas.gasto.model.EstadoMensualGasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Acceso a datos del estado mensual de los gastos.
 */
@Repository
public interface EstadoMensualGastoRepository extends JpaRepository<EstadoMensualGasto, Long> {

    /** Busca el estado de un gasto en un mes concreto (vacío si aún no se ha registrado). */
    Optional<EstadoMensualGasto> findByGasto_IdAndMes(Long gastoId, String mes);

    /** Devuelve todos los estados registrados en un mes. */
    List<EstadoMensualGasto> findByMes(String mes);
}