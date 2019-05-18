import logic.JsonReader;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class JsonReaderTest {

    @Test
    public void readJsonFromUrl() throws IOException, JSONException {
        String url = "http://api.nbp.pl/api/exchangerates/rates/A/USD/2015-06-01/?format=json";
        JSONObject jsonObject = JsonReader.readJsonFromUrl(url);
        float rate = (float)(((jsonObject.getJSONArray("rates")).getJSONObject(0)).getDouble("mid"));
        assertEquals(rate, 3.7850f, 0.01f);
    }
}