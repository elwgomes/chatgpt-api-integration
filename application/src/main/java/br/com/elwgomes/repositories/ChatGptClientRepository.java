package br.com.elwgomes.repositories;

import java.io.InputStream;

public interface ChatGptClientRepository {
    String execute(String topic, String message) throws Exception;
}
