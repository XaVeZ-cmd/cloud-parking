package one.digitalinnovatio.cloudparking.controller;

import one.digitalinnovatio.cloudparking.controller.dto.EstacionamentoCreateDTO;
import one.digitalinnovatio.cloudparking.controller.dto.EstacionamentoDTO;
import one.digitalinnovatio.cloudparking.controller.mapper.EstacionamentoMapper;
import one.digitalinnovatio.cloudparking.model.Estacionamento;
import one.digitalinnovatio.cloudparking.service.EstacionamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<EstacionamentoDTO>> findAll(){
        List<Estacionamento> estacionamentoList = estacionamentoService.findAll();
        List<EstacionamentoDTO> resultado = estacionamentoMapper.toEstacionamentoDTOList(estacionamentoList);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstacionamentoDTO> findById(@PathVariable String id){
        Estacionamento estacionamento = estacionamentoService.findById(id);
        EstacionamentoDTO resultado = estacionamentoMapper.toEstacionamentoDTO(estacionamento);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping
    public ResponseEntity<EstacionamentoDTO> create(@RequestBody EstacionamentoCreateDTO dto){
        var estacionamentoCreate = estacionamentoMapper.toEstacionamentoCreate(dto);
        Estacionamento estacionamento = estacionamentoService.create(estacionamentoCreate);
        EstacionamentoDTO resultado = estacionamentoMapper.toEstacionamentoDTO(estacionamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }
}
