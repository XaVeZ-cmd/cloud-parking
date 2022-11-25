package one.digitalinnovation.cloudparking.service;

import one.digitalinnovation.cloudparking.exception.EstacionamentoNotFoundException;
import one.digitalinnovation.cloudparking.model.Estacionamento;
import one.digitalinnovation.cloudparking.repository.EstacionamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EstacionamentoService {

        private final EstacionamentoRepository estacionamentoRepository;

    public EstacionamentoService(EstacionamentoRepository estacionamentoRepository) {
        this.estacionamentoRepository = estacionamentoRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Estacionamento> findAll(){
        return estacionamentoRepository.findAll();
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Transactional(readOnly = true)
    public Estacionamento findById(String id) {
        return estacionamentoRepository.findById(id).orElseThrow(() ->
                new EstacionamentoNotFoundException(id));
    }

    @Transactional
    public Estacionamento create(Estacionamento estacionamentoCreate) {
        String uuid = getUUID();
        estacionamentoCreate.setId(uuid);
        estacionamentoCreate.setHoraEntrada(LocalDateTime.now());
        estacionamentoRepository.save(estacionamentoCreate);
        return estacionamentoCreate;
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        estacionamentoRepository.deleteById(id);
    }

    @Transactional
    public Estacionamento update(String id, Estacionamento estacionamentoCreate) {
        Estacionamento estacionamento = findById(id);
        estacionamento.setCor(estacionamentoCreate.getCor());
        estacionamento.setPlaca(estacionamentoCreate.getPlaca());
        estacionamento.setEstado(estacionamentoCreate.getEstado());
        estacionamento.setModelo(estacionamentoCreate.getModelo());
        estacionamentoRepository.save(estacionamento);
        return estacionamento;
    }

    @Transactional
    public Estacionamento checkOut(String id) {
        Estacionamento estacionamento = findById(id);
        estacionamento.setHoraSaida(LocalDateTime.now());
        estacionamento.setConta(EstacionamentoCheckOut.getConta(estacionamento));
        estacionamentoRepository.save(estacionamento);
        return estacionamento;
    }
}
