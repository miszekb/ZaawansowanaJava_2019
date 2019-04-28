import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class CurrencyExchanger {

    public static float GetCurrencyRate(String currencyKey) throws JSONException, IOException
    {
        // currencyKey: USD (dolar amerykański), EUR (euro), CHF (frank szwajcarski), JPY (jen), MXN (peso), RUB (rubel)

        if ((!(currencyKey.equals("PLN"))) && (!(currencyKey.equals("pln")))) {

            String url = "http://api.nbp.pl/api/exchangerates/rates/A/" + currencyKey + "/?format=json";
            JSONObject json = JsonReader.readJsonFromUrl(url);
            float rate = (float)(((json.getJSONArray("rates")).getJSONObject(0)).getDouble("mid"));

            return rate;
        }
        return 1.0f;
    }

    public static float GetCurrencyRateOnDate(String currencyKey, String date) throws JSONException, IOException
    {
        // date format: rrrr-mm-dd  > 2004-03-01

        if ((!(currencyKey.equals("PLN"))) && (!(currencyKey.equals("pln")))) {

            String url = "http://api.nbp.pl/api/exchangerates/rates/A/" + currencyKey + "/" + date +"/?format=json";
            JSONObject json = JsonReader.readJsonFromUrl(url);
            float rate = (float)(((json.getJSONArray("rates")).getJSONObject(0)).getDouble("mid"));

            return rate;
        }
        return 1.0f;
    }
}
