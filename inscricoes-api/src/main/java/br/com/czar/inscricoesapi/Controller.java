package br.com.czar.inscricoesapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/inscricoesapi/inscricoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    private static final String API_URL = "http://localhost:8080/eventosfull/inscricoes"; // URL da API que você quer consumir

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    @ResponseBody
    public InscricoesDTO addInscricao(@RequestBody InscricoesDTO inscricoesDTO) {
        HttpHeaders headers = createHeadersWithAuth();

        HttpEntity<InscricoesDTO> requestEntity = new HttpEntity<>(inscricoesDTO, headers);

        ResponseEntity<InscricoesDTO> responseEntity = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, InscricoesDTO.class);

        return responseEntity.getBody();
    }

    @GetMapping
    @ResponseBody
    public List<InscricoesDTO> getAllInscricoes() {
        HttpHeaders headers = createHeadersWithAuth();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<InscricoesDTO[]> responseEntity = restTemplate.exchange(API_URL, HttpMethod.GET, entity, InscricoesDTO[].class);
        InscricoesDTO[] inscricoesArray = responseEntity.getBody();
        return Arrays.asList(inscricoesArray);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public InscricoesDTO getInscricaoById(@PathVariable("id") Integer id) {
        HttpHeaders headers = createHeadersWithAuth();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<InscricoesDTO> responseEntity = restTemplate.exchange(API_URL + "/" + id, HttpMethod.GET, entity, InscricoesDTO.class);
        return responseEntity.getBody();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteInscricaoById(@PathVariable("id") Integer id) {
        HttpHeaders headers = createHeadersWithAuth();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(API_URL + "/" + id, HttpMethod.DELETE, entity, String.class);
        return "Inscricao deletada com sucesso";
    }

    @PostMapping("/check-in")
    @ResponseBody
    public ResponseEntity<InscricoesDTO> checkin(@RequestBody InscricoesDTO inscricao) {
        String url = API_URL + "/check-in";
        HttpHeaders headers = createHeadersWithAuth();
        HttpEntity<InscricoesDTO> entity = new HttpEntity<>(inscricao, headers);
        ResponseEntity<InscricoesDTO> response = restTemplate.postForEntity(url, entity, InscricoesDTO.class);
        return response;
    }

    @PostMapping("/confirme")
    @ResponseBody
    public ResponseEntity<InscricoesDTO> confirme(@RequestBody InscricoesDTO inscricao) {
        String url = API_URL + "/confirme";
        HttpHeaders headers = createHeadersWithAuth();
        HttpEntity<InscricoesDTO> entity = new HttpEntity<>(inscricao, headers);
        ResponseEntity<InscricoesDTO> response = restTemplate.postForEntity(url, entity, InscricoesDTO.class);
        return response;
    }

    @PostMapping("/cancel")
    @ResponseBody
    public ResponseEntity<InscricoesDTO> updateInscription(@RequestBody InscricoesDTO inscricao) {
        String url = API_URL + "/cancel";
        HttpHeaders headers = createHeadersWithAuth();
        HttpEntity<InscricoesDTO> entity = new HttpEntity<>(inscricao, headers);
        ResponseEntity<InscricoesDTO> response = restTemplate.postForEntity(url, entity, InscricoesDTO.class);
        return response;
    }
    public HttpHeaders createHeadersWithAuth() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("user", "admin");
        headers.set("pass", "admin");
        return headers;
    }

}