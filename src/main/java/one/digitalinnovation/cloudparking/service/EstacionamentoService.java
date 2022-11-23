package one.digitalinnovation.cloudparking.service;

import one.digitalinnovation.cloudparking.exception.EstacionamentoNotFoundException;
import one.digitalinnovation.cloudparking.model.Estacionamento;
import one.digitalinnovation.cloudparking.repository.EstacionamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EstacionamentoService {

        private final EstacionamentoRepository estacionamentoRepository;

    public EstacionamentoService(EstacionamentoRepository estacionamentoRepository) {
        this.estacionamentoRepository = estacionamentoRepository;
    }

    public List<Estacionamento> findAll(){
        return estacionamentoRepository.findAll();
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public Estacionamento findById(String id) {
        return estacionamentoRepository.findById(id).orElseThrow(() ->
                new EstacionamentoNotFoundException(id));
    }

    public Estacionamento create(Estacionamento estacionamentoCreate) {
        String uuid = getUUID();
        estacionamentoCreate.setId(uuid);
        estacionamentoCreate.setHoraEntrada(LocalDateTime.now());
        estacionamentoRepository.save(estacionamentoCreate);
        return estacionamentoCreate;
    }

    public void delete(String id) {
        findById(id);
        estacionamentoRepository.deleteById(id);
    }

    public Estacionamento update(String id, Estacionamento estacionamentoCreate) {
        Estacionamento estacionamento = findById(id);
        estacionamento.setCor(estacionamentoCreate.getCor());
        estacionamento.setPlaca(estacionamentoCreate.getPlaca());
        estacionamento.setEstado(estacionamentoCreate.getEstado());
        estacionamento.setModelo(estacionamentoCreate.getModelo());
        estacionamentoRepository.save(estacionamento);
        return estacionamento;
    }

    public Estacionamento exit(String id) {
        //recuperar estacionado
        //atualizar data de saída
        //calcular o valor
        return null;
    }
}
