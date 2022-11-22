package one.digitalinnovatio.cloudparking.controller;

import one.digitalinnovatio.cloudparking.controller.dto.EstacionamentoDTO;
import one.digitalinnovatio.cloudparking.controller.mapper.EstacionamentoMapper;
import one.digitalinnovatio.cloudparking.model.Estacionamento;
import one.digitalinnovatio.cloudparking.service.EstacionamentoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    private final EstacionamentoService estacionamentoService;
    private final EstacionamentoMapper estacionamentoMapper;

    public EstacionamentoController(EstacionamentoService estacionamentoService, EstacionamentoMapper estacionamentoMapper) {
        this.estacionamentoService = estacionamentoService;
        this.estacionamentoMapper = estacionamentoMapper;
    }


    @GetMapping
    public List<EstacionamentoDTO> findAll(){
        List<Estacionamento> estacionamentoList = estacionamentoService.findAll();
        List<EstacionamentoDTO> resultado = estacionamentoMapper.toEstacionamentoDTOList(estacionamentoList);
        return resultado;
    }
}
