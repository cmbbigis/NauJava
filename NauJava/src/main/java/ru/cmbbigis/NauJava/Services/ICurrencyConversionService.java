package ru.cmbbigis.NauJava.Services;

import ru.cmbbigis.NauJava.Entities.CurrencyConversion;

import java.util.List;

public interface ICurrencyConversionService {
    void createConversion(String fromCurrency, String toCurrency, double conversionRate);
    CurrencyConversion findById(Long id);
    void updateConversion(Long id, String fromCurrency, String toCurrency, double conversionRate);
    void deleteById(Long id);
    List<CurrencyConversion> findAll();
}

