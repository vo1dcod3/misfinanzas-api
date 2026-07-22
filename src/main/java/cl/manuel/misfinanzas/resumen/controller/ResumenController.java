package cl.manuel.misfinanzas.resumen.controller;

import cl.manuel.misfinanzas.resumen.dto.ResumenResponseDTO;
import cl.manuel.misfinanzas.resumen.service.ResumenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resumen")
public class ResumenController {

    private final ResumenService resumenService;

    public ResumenController(ResumenService resumenService) {
        this.resumenService = resumenService;
    }

    @GetMapping("/{mes}")
    public ResponseEntity<ResumenResponseDTO> obtener(@PathVariable String mes) {
        return ResponseEntity.ok(resumenService.obtenerResumen(mes));
    }

}
