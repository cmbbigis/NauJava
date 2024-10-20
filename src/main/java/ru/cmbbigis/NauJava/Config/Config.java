package ru.cmbbigis.NauJava.Config;

import ru.cmbbigis.NauJava.Entities.CurrencyConversion;
import ru.cmbbigis.NauJava.CommandProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Configuration
public class Config {

    private final CommandProcessor commandProcessor;

    public Config(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<CurrencyConversion> currencyConversionContainer() {
        return new ArrayList<>();
    }

    @Bean
    public CommandLineRunner commandScanner() {
        return args -> {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Введите команду. 'exit' для выхода.");
                while (true) {
                    System.out.print("> ");
                    var input = scanner.nextLine();
                    if ("exit".equalsIgnoreCase(input.trim())) {
                        System.out.println("Выход из программы...");
                        break;
                    }
                    commandProcessor.processCommand(input);
                }
            }
        };
    }
}
