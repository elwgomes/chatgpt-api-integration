package br.com.elwgomes.adapters;

import br.com.elwgomes.ports.spi.GptPort;
import br.com.elwgomes.repositories.ChatGptClientRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GptAdapter implements GptPort {

    private ChatGptClientRepository repository;

    @Override
    public String getChatCompletion(String message) throws Exception {
        return repository.execute(message);
    }
}
