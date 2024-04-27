package br.com.elwgomes.ports.contract;

import java.io.InputStream;

public interface GetChatCompletionCommand {
    String execute(String topic, String message) throws Exception;
}
