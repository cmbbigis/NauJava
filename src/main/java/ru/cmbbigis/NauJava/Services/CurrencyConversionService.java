package ru.cmbbigis.NauJava.Services;

import ru.cmbbigis.NauJava.Entities.CurrencyConversion;
import ru.cmbbigis.NauJava.Repository.CurrencyConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyConversionService implements ICurrencyConversionService {

    private final CurrencyConversionRepository repository;

    @Autowired
    public CurrencyConversionService(@Lazy CurrencyConversionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createConversion(String fromCurrency, String toCurrency, double conversionRate) {
        var conversion = new CurrencyConversion(fromCurrency, toCurrency, conversionRate);
        repository.create(conversion);
    }

    @Override
    public CurrencyConversion findById(Long id) {
        return repository.read(id);
    }

    @Override
    public void updateConversion(Long id, String fromCurrency, String toCurrency, double conversionRate) throws Exception {
        var conversion = new CurrencyConversion(fromCurrency, toCurrency, conversionRate);
        conversion.setId(id);
        repository.update(conversion);
    }

    @Override
    public void deleteById(Long id) {
        repository.delete(id);
    }

    @Override
    public List<CurrencyConversion> findAll() {
        return repository.findAll();
    }
}

