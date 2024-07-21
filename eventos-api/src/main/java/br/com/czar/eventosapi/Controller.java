package br.com.czar.eventosapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping(value = "/eventosapi/eventos", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {
    @Autowired
    private RestTemplate restTemplate;

    private final String eventosUrl = "http://localhost:8080/eventosfull/eventos";

    @PostMapping
    @ResponseBody
    public EventosDTO addEvento(@RequestBody EventosDTO eventosDTO) {
        HttpHeaders headers = createHeadersWithAuth();

        HttpEntity<EventosDTO> requestEntity = new HttpEntity<>(eventosDTO, headers);

        ResponseEntity<EventosDTO> responseEntity = restTemplate.exchange(eventosUrl, HttpMethod.POST, requestEntity, EventosDTO.class);

        return responseEntity.getBody();
    }

    @PutMapping("/{eventoId}")
    @ResponseBody
    public EventosDTO updateEvento(@PathVariable("eventoId") int eventoId, @RequestBody EventosDTO eventosDTO) {
        String url = eventosUrl + "/" + eventoId;
        HttpHeaders headers = createHeadersWithAuth();
        HttpEntity<EventosDTO> entity = new HttpEntity<>(eventosDTO, headers);
        restTemplate.put(url, entity);
        return eventosDTO;
    }

    @GetMapping
    @ResponseBody
    public List<EventosDTO> getAllEventos() {
        HttpHeaders headers = createHeadersWithAuth();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return Arrays.asList(restTemplate.exchange(eventosUrl, HttpMethod.GET, entity, EventosDTO[].class).getBody());
    }

    @GetMapping("/{eventoId}")
    @ResponseBody
    public EventosDTO getEventoById(@PathVariable("eventoId") int eventoId) {
        String url = eventosUrl + "/" + eventoId;
        HttpHeaders headers = createHeadersWithAuth();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<EventosDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, EventosDTO.class);
        return  responseEntity.getBody();
    }

    @DeleteMapping("/{eventoId}")
    @ResponseBody
    public String deleteEventoById(@PathVariable("eventoId") int eventoId) {
        String url = eventosUrl + "/" + eventoId;
        HttpHeaders headers = createHeadersWithAuth();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
        return "Evento removido com sucesso";
    }

    public HttpHeaders createHeadersWithAuth() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("user", "admin");
        headers.set("pass", "admin");
        return headers;
    }
}
