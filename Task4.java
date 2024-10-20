import java.net.URI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Task4 {
    public static void main(String[] args) {
        try {
            var uri = new URI("https://httpbin.org/user-agent");

            var connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("GET");

            var input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            var inputLine = "";
            var content = new StringBuilder();
            while ((inputLine = input.readLine()) != null) {
                content.append(inputLine);
            }

            input.close();
            connection.disconnect();

            var response = content.toString();
            var userAgent = response.split("\"user-agent\": \"")[1].split("\"")[0];

            System.out.println("User-Agent: " + userAgent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
