package one.digitalinnovatio.cloudparking.service;

import one.digitalinnovatio.cloudparking.model.Estacionamento;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EstacionamentoService {

    private static Map<String, Estacionamento> estacionamentoMap = new HashMap();

    static {
        var id = getUUID();
        Estacionamento estacionamento = new Estacionamento(id, "PDD-3779", "RENAULT KWID", "BRANCA", "PE");
        estacionamentoMap.put(id, estacionamento);
    }

        public List<Estacionamento> findAll(){
            return estacionamentoMap.values().stream().collect(Collectors.toList());
        }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
