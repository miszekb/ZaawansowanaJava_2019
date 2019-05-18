package logic;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class JsonReader {

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream inputStream = new URL(url).openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        String jsonText = readAllTextFromBuffer(bufferedReader);
        JSONObject jsonObject = new JSONObject(jsonText);
        inputStream.close();
        return jsonObject;
    }

    private static String readAllTextFromBuffer(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int getChar;

        while ((getChar = reader.read()) != -1) {
            stringBuilder.append(((char) getChar));
        }

        return stringBuilder.toString();
    }
}
