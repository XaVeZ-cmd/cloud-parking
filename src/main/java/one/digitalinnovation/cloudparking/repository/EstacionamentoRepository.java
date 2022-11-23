package one.digitalinnovation.cloudparking.repository;

import one.digitalinnovation.cloudparking.model.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento, String> {
}
