package br.com.czar.usuariosapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/usuariosapi/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {
    @Autowired
    private RestTemplate restTemplate;


    private final String user = "admin";
    private final String pass = "admin";
    private final String usuariosUrl = "http://localhost:8080/eventosfull/usuarios";

    @PostMapping
    @ResponseBody
    public UsuariosDTO addUsuario(@RequestBody UsuariosDTO usuariosDTO) {
        HttpHeaders headers = createHeadersWithAuth();
        HttpEntity<UsuariosDTO> requestEntity = new HttpEntity<>(usuariosDTO, headers);
        return restTemplate.postForObject(usuariosUrl, requestEntity, UsuariosDTO.class);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public UsuariosDTO updateUsuario(@PathVariable("id") Integer id, @RequestBody UsuariosDTO usuariosDTO) {
        String url = usuariosUrl + "/" + id;
        HttpHeaders headers = createHeadersWithAuth();
        HttpEntity<UsuariosDTO> entity = new HttpEntity<>(usuariosDTO, headers);
        restTemplate.put(url, entity);
        return usuariosDTO;
    }

    @GetMapping
    @ResponseBody
    public List<UsuariosDTO> getAllUsuarios() {
        HttpHeaders headers = createHeadersWithAuth();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return Arrays.asList(restTemplate.exchange(usuariosUrl, HttpMethod.GET, entity, UsuariosDTO[].class).getBody());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public List<UsuariosDTO> getUsuarioById(@PathVariable("id") Integer id) {
        String url = usuariosUrl + "/" + id;
        HttpHeaders headers = createHeadersWithAuth();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<UsuariosDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, UsuariosDTO.class);
        return Arrays.asList(responseEntity.getBody());
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteUsuarioById(@PathVariable("id") Integer id) {
        String url = usuariosUrl + "/" + id;
        HttpHeaders headers = createHeadersWithAuth();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
        return "Usuário removido com sucesso";
    }


    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody UsuariosDTO usuariosDTO) {
        String url = usuariosUrl + "/login";
        HttpHeaders headers = createHeadersWithAuth();
        HttpEntity<UsuariosDTO> requestEntity = new HttpEntity<>(usuariosDTO, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return responseEntity.getBody();
    }

    public HttpHeaders createHeadersWithAuth() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("user", user);
        headers.set("pass", pass);
        return headers;
    }

}
