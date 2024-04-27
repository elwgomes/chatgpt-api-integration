package br.com.elwgomes.ports.spi;

import java.io.InputStream;

public interface GptPort {
    String getChatCompletion (String topic, String message) throws Exception;
}
