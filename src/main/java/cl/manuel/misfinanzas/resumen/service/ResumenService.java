package cl.manuel.misfinanzas.resumen.service;

import cl.manuel.misfinanzas.gasto.model.CategoriaGasto;
import cl.manuel.misfinanzas.gasto.model.EstadoMensualGasto;
import cl.manuel.misfinanzas.gasto.model.Gasto;
import cl.manuel.misfinanzas.gasto.repository.EstadoMensualGastoRepository;
import cl.manuel.misfinanzas.gasto.repository.GastoRepository;
import cl.manuel.misfinanzas.ingreso.model.Ingreso;
import cl.manuel.misfinanzas.ingreso.repository.IngresoRepository;
import cl.manuel.misfinanzas.resumen.dto.DesgloseCategoriaDTO;
import cl.manuel.misfinanzas.resumen.dto.ResumenResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResumenService {

    private final IngresoRepository ingresoRepository;
    private final GastoRepository gastoRepository;
    private final EstadoMensualGastoRepository estadoMensualGastoRepository;

    public ResumenService(IngresoRepository ingresoRepository, GastoRepository gastoRepository, EstadoMensualGastoRepository estadoMensualGastoRepository) {
        this.ingresoRepository = ingresoRepository;
        this.gastoRepository = gastoRepository;
        this.estadoMensualGastoRepository = estadoMensualGastoRepository;
    }

    public ResumenResponseDTO obtenerResumen( String mes ) {

        List<Gasto> gastos = gastoRepository.findByActivoTrue();

        // Ingreso total: suma de los montos de los ingresos activos
        Long ingresoTotal = ingresoRepository.findByActivoTrue().stream()
                .mapToLong(Ingreso::getMonto)
                .sum();

        // Gastos propios: suma de miCosto (mitad si compartido)
        Long gastosPropios = gastos.stream()
                .mapToLong(Gasto::getMiCosto)
                .sum();

        // Reembolso: la otra mitad de los gastos compartidos
        Long reembolso = gastos.stream()
                .filter(Gasto::getCompartido)
                .mapToLong(g -> g.getMonto() / 2)
                .sum();

        Long ahorro = ingresoTotal - gastosPropios;


        // Pagado del mes: miCosto de los gastos con estado pagado ese mes
        Long pagado= estadoMensualGastoRepository.findByMes(mes).stream()
                .filter(EstadoMensualGasto::isPagado)
                .filter(e -> e.getGasto().getActivo())
                .mapToLong(e -> e.getGasto().getMiCosto())
                .sum();

        Long pendiente = gastosPropios - pagado;

        // Desglose: por cada categoría, la suma de miCosto
        List<DesgloseCategoriaDTO> desglose = new ArrayList<>();
        for (CategoriaGasto categoriaGasto: CategoriaGasto.values()) {
            Long total = gastos.stream()
                    .filter(g -> g.getCategoria() == categoriaGasto)
                    .mapToLong(Gasto::getMiCosto)
                    .sum();
            desglose.add(new DesgloseCategoriaDTO(categoriaGasto, total));
        }

        return new ResumenResponseDTO(mes, ingresoTotal, gastosPropios, reembolso, ahorro, pagado, pendiente, desglose);


    }
}
