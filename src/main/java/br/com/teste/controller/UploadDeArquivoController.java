package br.com.teste.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping(value = "/api/upload")
@Slf4j

public class UploadDeArquivoController {

    @PostMapping()
    public ResponseEntity<String> salvarArquivo(@RequestParam MultipartFile file){
        log.info("recebendo o arquivo: " + file.getOriginalFilename());
        var path = ("C:/Users/Johnny/Desktop/teste/imagens/"+file.getOriginalFilename());
        log.info("Novo nome do arquivo: " + file.getOriginalFilename());
        try{
            Files.copy(file.getInputStream(), Path.of(path), StandardCopyOption.REPLACE_EXISTING);
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
