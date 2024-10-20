package ru.cmbbigis.NauJava.Repository;

import ru.cmbbigis.NauJava.Entities.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

@Component
public class CurrencyConversionRepository implements CrudRepository<CurrencyConversion, Long> {

    private final List<CurrencyConversion> currencyConversionContainer;

    @Autowired
    public CurrencyConversionRepository(List<CurrencyConversion> currencyConversionContainer) {
        this.currencyConversionContainer = currencyConversionContainer;
    }

    @Override
    public void create(CurrencyConversion currencyConversion) {
        currencyConversionContainer.add(currencyConversion);
    }

    @Override
    public CurrencyConversion read(Long id) {
        return currencyConversionContainer.stream()
                .filter(currencyConversion -> currencyConversion.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(CurrencyConversion currencyConversion) throws Exception {
        var currencyConversionFromDb = read(currencyConversion.getId());
        if (currencyConversionFromDb == null) {
            throw new Exception("currencyConversion was not find in database");
        }
        var index = currencyConversionContainer.indexOf(currencyConversionFromDb);
        if (index >= 0) {
            currencyConversionContainer.set(index, currencyConversion);
        }
    }

    @Override
    public void delete(Long id) {
        currencyConversionContainer.removeIf(currencyConversion -> currencyConversion.getId().equals(id));
    }

    @Override
    public List<CurrencyConversion> findAll() {
        return new ArrayList<>(currencyConversionContainer);
    }
}
