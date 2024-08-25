package com.example.demo.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversion {
    int fromId;
    int toId;
    String fromCurrency;
    String toCurrency;
    float ratio;
}
