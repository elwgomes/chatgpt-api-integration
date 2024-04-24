package br.com.elwgomes.http;

import br.com.elwgomes.repositories.ChatGptClientRepository;
import lombok.*;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@AllArgsConstructor
@Getter
public class ChatGptHttpClient implements ChatGptClientRepository {
    private final String apiKey;
    private final String endpoint;
    private final String model;

    public static ChatGptHttpClient fromConfig(InputStream configFile) {
        Yaml yaml = new Yaml();
        Map<String, Object> config = yaml.load(configFile);
        Map<String, Object> chatgptConfig = (Map<String, Object>) config.get("chatgpt");
        Map<String, Object> apiConfig = (Map<String, Object>) chatgptConfig.get("api");
        String apiKey = (String) apiConfig.get("key");
        String endpoint = (String) apiConfig.get("endpoint");
        String model = (String) apiConfig.get("model");
        return new ChatGptHttpClient(apiKey, endpoint, model);
    }

    @Override
    public String execute(String message) throws Exception {
        try {
            URL obj = new URL(endpoint);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");

            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
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
            return "ChatGPT: " + extractContentFromResponse(response.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractContentFromResponse(String response) {
        int startMarker = response.indexOf("content")+11;
        int endMarker = response.indexOf("\"", startMarker);
        return response.substring(startMarker, endMarker);
    }
}
