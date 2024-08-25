package com.example.demo.controller;

import com.example.demo.Model.Currency;
import com.example.demo.Model.CurrencyConversion;
import com.example.demo.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CurrencyExchange {
    @Autowired
    CurrencyService currencyService;
    @GetMapping("/currency")
    public ResponseEntity<List<Currency>>getAllCurrency(){
        return new ResponseEntity<>(currencyService.getAll(), HttpStatusCode.valueOf(200));
    }
    @GetMapping("/currency/{id}")
    public ResponseEntity<Currency>getCurrencyById(@PathVariable int id){
        return new ResponseEntity<>(currencyService.getById(id), HttpStatusCode.valueOf(200));
    }
    @PostMapping("/currency")
    public ResponseEntity<String> createCurrency(@RequestBody Currency currency){
        currencyService.createCurrency(currency);
        return new ResponseEntity<>( "Created",HttpStatusCode.valueOf(201));
    }
    @PutMapping("/currency")
    public ResponseEntity<String> updateCurrency(@RequestBody Currency currency){
        currencyService.updateCurrency(currency);
        return new ResponseEntity<>( "Updated",HttpStatusCode.valueOf(204));
    }
    @DeleteMapping("/currency/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){
        if(null != currencyService.getById(id)) {
            currencyService.deleteById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/convertCurrency")
    public ResponseEntity<CurrencyConversion> conversion(@RequestBody CurrencyConversion currencyConversion){
        if(null!=currencyConversion.getToCurrency() && null!=currencyConversion.getFromCurrency()){
            System.out.println("name");
            System.out.println(currencyConversion);
            return new ResponseEntity<>(currencyService.getConversionByName(currencyConversion), HttpStatusCode.valueOf(200));
        }else if(currencyConversion.getToId() >0&& 0<currencyConversion.getFromId()){
            System.out.println("id");
            System.out.println(currencyConversion);
            return new ResponseEntity<>(currencyService.getConversionById(currencyConversion), HttpStatusCode.valueOf(200));
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
