package br.com.elwgomes.ports.contract;

public interface GetChatCompletionCommand {
    String execute(String message) throws Exception;
}
