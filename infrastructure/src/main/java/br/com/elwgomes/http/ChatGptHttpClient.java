package br.com.elwgomes.http;

import br.com.elwgomes.config.ConfigReader;
import br.com.elwgomes.repositories.ChatGptClientRepository;
import lombok.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@AllArgsConstructor
@Getter
@Builder
public class ChatGptHttpClient extends ConfigReader implements ChatGptClientRepository {
    private final String apiKey;
    private final String endpoint;
    private final String model;

    @Override
    public String execute(String topic, String message) throws Exception {
        try {
            URL obj = new URL(endpoint);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");

            String prompt = "Vou te perguntar algo e se não tiver a ver com " + topic + ", você não precisa responder, tudo bem? Apenas diga: 'Este assunto não me diz respeito.' Se o assunto tiver alguma relação com " + topic + ", você pode responder normalmente, ok? " + message;
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // extrai a resposta da api
            int startMarker = response.indexOf("content")+11;
            int endMarker = response.indexOf("\"", startMarker);
            String extractedResponse= response.substring(startMarker, endMarker);

            System.out.println(prompt);

            return "ChatGPT: " + extractedResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
