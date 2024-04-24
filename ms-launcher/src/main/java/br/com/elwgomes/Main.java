package br.com.elwgomes;

import br.com.elwgomes.http.ChatGptHttpClient;
import br.com.elwgomes.repositories.ChatGptClientRepository;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

@Slf4j
public class Main {

    private static ChatGptClientRepository repository;

    public static void main(String[] args) throws Exception {
            InputStream configFile = Main.class.getClassLoader().getResourceAsStream("application.yaml");
            repository = ChatGptHttpClient.fromConfig(configFile);
            System.out.println(repository.execute("Hello world."));
    }
}
