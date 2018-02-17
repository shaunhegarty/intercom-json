import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class JSONFixer {

    public static String readCustomerJsonFile(String url) throws IOException {

        URL oracle = new URL(url);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()))){

            StringBuilder json = new StringBuilder();
            //json file has multiple root elements
            json.append("[ ");
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                json.append(", ");
                json.append(inputLine);
            }
            json.append(" ]");

            return json.toString().replaceFirst(",", "");
        }
    }
}
