package com.example.demo.service;

import com.example.demo.Model.Currency;
import com.example.demo.Model.CurrencyConversion;
import com.example.demo.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public List<Currency> getAll(){
        return currencyRepository.findAll();
    }
    public Currency getById(int id){
        return currencyRepository.findById(id).orElse(new Currency(0,"Default",1));
    }
    public CurrencyConversion getConversionById(CurrencyConversion cc){
        Currency fromCC=currencyRepository.findById(cc.getFromId()).orElse(new Currency(0,"Default",1));
        Currency toCC=currencyRepository.findById(cc.getToId()).orElse(new Currency(0,"Default",1));
        cc.setRatio(fromCC.getConversionRatio()/toCC.getConversionRatio());
        return cc;
    }

    public CurrencyConversion getConversionByName(CurrencyConversion cc){
        Currency fromCC=currencyRepository.getConversionByCurrency(cc.getFromCurrency());
        Currency toCC=currencyRepository.getConversionByCurrency(cc.getToCurrency());
        cc.setRatio(fromCC.getConversionRatio()/toCC.getConversionRatio());
        return cc;
    }
    public void deleteById(int id){
        currencyRepository.deleteById(id);
    }
    public Currency updateCurrency(Currency currency){
        return currencyRepository.save(currency);
    }
    public void createCurrency(Currency currency){
        currencyRepository.save(currency);
    }
}
