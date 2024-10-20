package ru.cmbbigis.NauJava;

import ru.cmbbigis.NauJava.Services.ICurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandProcessor {

    private final ICurrencyConversionService service;

    @Autowired
    public CommandProcessor(ICurrencyConversionService service) {
        this.service = service;
    }

    public void processCommand(String input) throws Exception {
        var cmd = input.split(" ");
        switch (cmd[0]) {
            case "create" -> {
                var fromCurrency = cmd[1];
                var toCurrency = cmd[2];
                var conversionRate = Double.parseDouble(cmd[3]);
                service.createConversion(fromCurrency, toCurrency, conversionRate);
                System.out.println("Конверсия успешно добавлена...");
            }
            case "read" -> {
                var id = Long.valueOf(cmd[1]);
                var conversion = service.findById(id);
                if (conversion == null) {
                    System.out.println("Конверсия не найдена...");
                } else {
                    System.out.printf("CurrencyConversion = {id: %d, fromCurrency: %s, toCurrency: %s, conversionRate: %f}%n",
                            conversion.getId(), conversion.getFromCurrency(), conversion.getToCurrency(), conversion.getConversionRate());
                }
            }
            case "update" -> {
                var id = Long.valueOf(cmd[1]);
                var fromCurrency = cmd[2];
                var toCurrency = cmd[3];
                var conversionRate = Double.parseDouble(cmd[4]);
                service.updateConversion(id, fromCurrency, toCurrency, conversionRate);
                System.out.println("Конверсия успешно обновлена...");
            }
            case "delete" -> {
                var id = Long.valueOf(cmd[1]);
                service.deleteById(id);
                System.out.println("Конверсия успешно удалена...");
            }
            case "list" -> {
                for (var currencyConversion : service.findAll()) {
                    System.out.printf("CurrencyConversion = {id: %d, fromCurrency: %s, toCurrency: %s, conversionRate: %f}%n",
                            currencyConversion.getId(), currencyConversion.getFromCurrency(), currencyConversion.getToCurrency(), currencyConversion.getConversionRate());
                }
            }
            default -> System.out.println("Введена неизвестная команда...");
        }
    }
}

