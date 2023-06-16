package com.srdevpereira.controller;

import com.srdevpereira.entities.Coin;
import com.srdevpereira.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/coin")
public class CoinController {

    @Autowired
    private CoinRepository coinRepository;

    @GetMapping()
    public ResponseEntity get(){
        return new ResponseEntity<>(coinRepository.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity get(@PathVariable String name){
        try{
            return new ResponseEntity<>(coinRepository.getByName(name), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping() //com só teremos 1 rota de post não se faz necessario determinar um novo end-point para ele
    public ResponseEntity post(@RequestBody Coin coin){ //esta anotação define que os detalhes da requisição estarão no corpo do arquivo Json
        try{
            coin.setDateTime(new Timestamp(System.currentTimeMillis()));//define a data sistemica do momento da requisição
            return new ResponseEntity<>(coinRepository.insert(coin), HttpStatus.CREATED);
        }
        catch (Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
