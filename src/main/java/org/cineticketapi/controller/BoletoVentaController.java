package org.cineticketapi.controller;

import lombok.RequiredArgsConstructor;
import org.cineticketapi.dto.BoletoVentaReqDto;
import org.cineticketapi.dto.BoletoVentaRespDto;
import org.cineticketapi.service.BoletoVentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boletoVenta")
@RequiredArgsConstructor
public class BoletoVentaController {

    private final BoletoVentaService boletoVentaService;

    @GetMapping
    public ResponseEntity<List<BoletoVentaRespDto>> getAll() {
        return ResponseEntity.ok(boletoVentaService.getBoletos());
    }

    @GetMapping("/{idBoleto}/{idVenta}")
    public ResponseEntity<BoletoVentaRespDto> getById(@PathVariable Long idBoleto,
                                                      @PathVariable Long idVenta) {

        return boletoVentaService.getBoletoById(idBoleto, idVenta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BoletoVentaRespDto> create(@RequestBody BoletoVentaReqDto dto) {
        return boletoVentaService.createBoleto(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{idBoleto}/{idVenta}")
    public ResponseEntity<String> delete(@PathVariable Long idBoleto,
                                         @PathVariable Long idVenta) {

        boletoVentaService.deleteBoleto(idBoleto, idVenta);
        return ResponseEntity.ok("Boleto eliminado con éxito");
    }
}
