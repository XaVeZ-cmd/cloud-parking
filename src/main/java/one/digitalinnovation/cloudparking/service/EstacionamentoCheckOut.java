package one.digitalinnovation.cloudparking.service;

import one.digitalinnovation.cloudparking.model.Estacionamento;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class EstacionamentoCheckOut {

    public static final int UMA_HORA = 60;
    public static final int VINTE_E_QUATRO_HORAS = 24 * UMA_HORA;
    public static final double VALOR_UMA_HORA = 5.00;
    public static final double ADICIONAL_POR_HORA = 2.00;
    public static final double DIARIA_VALOR = 20.00;


    public static Double getConta(Estacionamento estacionamento){
        return getConta(estacionamento.getHoraEntrada(), estacionamento.getHoraSaida());
    }

    private static Double getConta(LocalDateTime horaEntrada, LocalDateTime horaSaida) {
        long minutos = horaEntrada.until(horaSaida, ChronoUnit.MINUTES);
        Double conta = 0.0;
        if (minutos <= UMA_HORA) {
            return VALOR_UMA_HORA;
        }
        if (minutos <= VINTE_E_QUATRO_HORAS) {
            conta = VALOR_UMA_HORA;
            int hours = (int) (minutos / UMA_HORA);
            for (int i = 0; i < hours; i++) {
                conta += ADICIONAL_POR_HORA;
            }
            return conta;
        }
        int days = (int) (minutos / VINTE_E_QUATRO_HORAS);
        for (int i = 0; i < days; i++) {
            conta += DIARIA_VALOR;
        }
        return conta;
    }
}
