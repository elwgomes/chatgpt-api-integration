package br.com.elwgomes.usecases;

import br.com.elwgomes.ports.contract.GetChatCompletionCommand;
import br.com.elwgomes.ports.spi.GptPort;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;

@RequiredArgsConstructor
public class GetChatCompletionHandler implements GetChatCompletionCommand {

    private GptPort gptPort;

    @Override
    public String execute(String topic, String message) throws Exception {
        return gptPort.getChatCompletion(topic, message);
    }
}
