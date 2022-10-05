package br.com.teste.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/upload", produces = {"application/json"})
@Slf4j
@CrossOrigin("*")
public class UploadDeArquivoController {

    @PostMapping("/arquivo")
    public ResponseEntity<String> salvarArquivo(@RequestParam ("file")MultipartFile file){
        log.info("recebendo o arquivo: ", file.getOriginalFilename());
        var path = "/home";
        var caminho = path + UUID.randomUUID() +" . "+ extrairExtensao(file.getOriginalFilename());
        log.info("Novo nome do arquivo: " + caminho);
        try{
            Files.copy(file.getInputStream(), Path.of(caminho), StandardCopyOption.REPLACE_EXISTING);
            return new ResponseEntity<>("{ \"mensagem\" :  \"Arquivo carregado com sucesso!\"}" , HttpStatus.OK);
        }catch (Exception e){
            log.info("Erro ao processar arquivo " , e);
            return new ResponseEntity<>("{ \"mensagem\" :  \"Arquivo carregado com sucesso!\"}" , HttpStatus.BAD_REQUEST);
        }
    }

    private String extrairExtensao(String originalFilename) {
        int i = originalFilename.lastIndexOf(".");
        return originalFilename.substring(i + 1);
    }

}
