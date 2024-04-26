package br.com.elwgomes.config;

import br.com.elwgomes.http.ChatGptHttpClient;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class ConfigReader {
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
}
