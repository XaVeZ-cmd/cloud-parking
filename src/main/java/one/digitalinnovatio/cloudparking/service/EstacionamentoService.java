package one.digitalinnovatio.cloudparking.service;

import one.digitalinnovatio.cloudparking.model.Estacionamento;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EstacionamentoService {

    private static Map<String, Estacionamento> estacionamentoMap = new HashMap();

    static {
        var id = getUUID();
        var id1 = getUUID();
        Estacionamento estacionamento = new Estacionamento(id, "PDD-3779", "RENAULT KWID", "BRANCA", "PE");
        Estacionamento estacionamento1 = new Estacionamento(id1, "PFM-2729", "CHEVROLET CLASSIC", "PRETA", "PE");
        estacionamentoMap.put(id, estacionamento);
        estacionamentoMap.put(id1, estacionamento1);
    }

        public List<Estacionamento> findAll(){
            return estacionamentoMap.values().stream().collect(Collectors.toList());
        }



    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public Estacionamento findById(String id) {
        return estacionamentoMap.get(id);
    }

    public Estacionamento create(Estacionamento estacionamentoCreate) {
        String uuid = getUUID();
        estacionamentoCreate.setId(uuid);
        estacionamentoCreate.setHoraEntrada(LocalDateTime.now());
        estacionamentoMap.put(uuid, estacionamentoCreate);
        return estacionamentoCreate;
    }
}
