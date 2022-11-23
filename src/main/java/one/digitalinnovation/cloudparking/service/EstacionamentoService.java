package one.digitalinnovation.cloudparking.service;

import one.digitalinnovation.cloudparking.exception.EstacionamentoNotFoundException;
import one.digitalinnovation.cloudparking.model.Estacionamento;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EstacionamentoService {

    private static Map<String, Estacionamento> estacionamentoMap = new HashMap();

        public List<Estacionamento> findAll(){
            return estacionamentoMap.values().stream().collect(Collectors.toList());
        }



    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public Estacionamento findById(String id) {
        Estacionamento estacionamento = estacionamentoMap.get(id);
        if(estacionamento ==null){
            throw new EstacionamentoNotFoundException(id);
        }
        return estacionamento;
    }

    public Estacionamento create(Estacionamento estacionamentoCreate) {
        String uuid = getUUID();
        estacionamentoCreate.setId(uuid);
        estacionamentoCreate.setHoraEntrada(LocalDateTime.now());
        estacionamentoMap.put(uuid, estacionamentoCreate);
        return estacionamentoCreate;
    }

    public void delete(String id) {
        findById(id);
        estacionamentoMap.remove(id);
    }

    public Estacionamento update(String id, Estacionamento estacionamentoCreate) {
        Estacionamento estacionamento = findById(id);
        estacionamento.setCor(estacionamentoCreate.getCor());
        estacionamentoMap.replace(id, estacionamento);
        return estacionamento;
    }

    public Estacionamento exit(String id) {
        //recuperar estacionado
        //atualizar data de saída
        //calcular o valor
        return null;
    }
}
