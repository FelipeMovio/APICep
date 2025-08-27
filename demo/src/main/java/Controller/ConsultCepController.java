package Controller;

import dto.CepResultDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consulta-cep")
public class ConsultCepController {

    @GetMapping("{cep}")
    public CepResultDTO consultaCep (@PathVariable("cep") String cep){
        RestTemplate restTemplate = new RestTemplate(); // Criação do bjeto para trabalhar com restTemplate
        ResponseEntity<CepResultDTO> resp =
                restTemplate
                        .getForEntity(
                                String.format("viacep.com.br/ws/01001000/json/", cep),
                                CepResultDTO.class);
        return resp.getBody();
    }
}
