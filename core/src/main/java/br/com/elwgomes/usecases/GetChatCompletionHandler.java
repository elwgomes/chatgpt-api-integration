package br.com.elwgomes.usecases;

import br.com.elwgomes.ports.contract.GetChatCompletionCommand;
import br.com.elwgomes.ports.spi.GptPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetChatCompletionHandler implements GetChatCompletionCommand {

    private GptPort gptPort;

    @Override
    public String execute(String message) throws Exception {
        return gptPort.getChatCompletion(message);
    }
}
