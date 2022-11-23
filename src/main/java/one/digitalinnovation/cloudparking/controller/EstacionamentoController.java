package one.digitalinnovation.cloudparking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.cloudparking.controller.dto.EstacionamentoCreateDTO;
import one.digitalinnovation.cloudparking.controller.dto.EstacionamentoDTO;
import one.digitalinnovation.cloudparking.controller.mapper.EstacionamentoMapper;
import one.digitalinnovation.cloudparking.model.Estacionamento;
import one.digitalinnovation.cloudparking.service.EstacionamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estacionamento")
@Api(tags = "Estacionamento Controller")
public class EstacionamentoController {

    private final EstacionamentoService estacionamentoService;
    private final EstacionamentoMapper estacionamentoMapper;

    public EstacionamentoController(EstacionamentoService estacionamentoService, EstacionamentoMapper estacionamentoMapper) {
        this.estacionamentoService = estacionamentoService;
        this.estacionamentoMapper = estacionamentoMapper;
    }

    @GetMapping
    @ApiOperation("Buscar todos estacionamentos")
    public ResponseEntity<List<EstacionamentoDTO>> findAll(){
        List<Estacionamento> estacionamentoList = estacionamentoService.findAll();
        List<EstacionamentoDTO> resultado = estacionamentoMapper.toEstacionamentoDTOList(estacionamentoList);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    @ApiOperation("Buscar estacionamento por id")
    public ResponseEntity<EstacionamentoDTO> findById(@PathVariable String id){
        Estacionamento estacionamento = estacionamentoService.findById(id);
        EstacionamentoDTO resultado = estacionamentoMapper.toEstacionamentoDTO(estacionamento);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping
    @ApiOperation("Criar estacionamento")
    public ResponseEntity<EstacionamentoDTO> create(@RequestBody EstacionamentoCreateDTO dto){
        var estacionamentoCreate = estacionamentoMapper.toEstacionamentoCreate(dto);
        Estacionamento estacionamento = estacionamentoService.create(estacionamentoCreate);
        EstacionamentoDTO resultado = estacionamentoMapper.toEstacionamentoDTO(estacionamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }
}
