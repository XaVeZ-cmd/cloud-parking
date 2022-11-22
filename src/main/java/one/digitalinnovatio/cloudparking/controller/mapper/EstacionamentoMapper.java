package one.digitalinnovatio.cloudparking.controller.mapper;

import one.digitalinnovatio.cloudparking.controller.EstacionamentoController;
import one.digitalinnovatio.cloudparking.controller.dto.EstacionamentoDTO;
import one.digitalinnovatio.cloudparking.model.Estacionamento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstacionamentoMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public EstacionamentoDTO estacionamentoDTO(Estacionamento estacionamento){
        return MODEL_MAPPER.map(estacionamento, EstacionamentoDTO.class);
    }
    public List<EstacionamentoDTO> toEstacionamentoDTOList(List<Estacionamento> estacionamentoList){
        return estacionamentoList.stream().map(this::estacionamentoDTO).collect(Collectors.toList());
    }
}
