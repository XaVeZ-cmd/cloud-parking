package one.digitalinnovation.cloudparking.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import io.restassured.RestAssured;
import one.digitalinnovation.cloudparking.controller.dto.EstacionamentoCreateDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;

//Porta aleatória para rodar os testes sem usar a 8080
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EstacionamentoControllerTest {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest(){
        System.out.println(randomPort);
        RestAssured.port = randomPort;
    }

    @Test
    void quandoFizerFindAllChequeOResultado() {
        RestAssured.given()
                .when()
                .get("/estacionamento")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void quandoFizerCreateChequeCriado() {

        var createDTO = new EstacionamentoCreateDTO();
        createDTO.setCor("AMARELA");
        createDTO.setPlaca("JFH-5585");
        createDTO.setModelo("BRASÍLIA");
        createDTO.setEstado("SP");
        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/estacionamento")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("cor", Matchers.equalTo("AMARELA"));
    }
}