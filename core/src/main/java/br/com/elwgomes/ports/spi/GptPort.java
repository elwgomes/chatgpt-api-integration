package br.com.elwgomes.ports.spi;

public interface GptPort {
    String getChatCompletion (String message) throws Exception;
}
