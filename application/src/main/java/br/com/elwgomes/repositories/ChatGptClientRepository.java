package br.com.elwgomes.repositories;

public interface ChatGptClientRepository {
    String execute(String message) throws Exception;
}
