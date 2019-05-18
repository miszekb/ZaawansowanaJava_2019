package logic;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class CurrencyExchanger {

    // currencyKey: USD, EUR, CHF, JPY, MXN, RUB
    public static float getCurrencyRate(String currencyKey) {
        if ((!(currencyKey.equals("PLN"))) && (!(currencyKey.equals("pln")))) {

            String url = "http://api.nbp.pl/api/exchangerates/rates/A/" + currencyKey + "/?format=json";

            return getCurrency(url);
        }
        return 1.0f;
    }

    // date format: rrrr-mm-dd > 2004-03-01
    public static float getCurrencyRateOnDate(String currencyKey, String date) {
        if ((!(currencyKey.equals("PLN"))) && (!(currencyKey.equals("pln")))) {

            String url = "http://api.nbp.pl/api/exchangerates/rates/A/" + currencyKey + "/" + date +"/?format=json";

            return getCurrency(url);
        }
        return 1.0f;
    }

    private static float getCurrency(String url) {

        float rate;

        try {
            JSONObject json = JsonReader.readJsonFromUrl(url);
            rate = (float)(((json.getJSONArray("rates")).getJSONObject(0)).getDouble("mid"));
        } catch (JSONException exception) {
            System.out.println(exception.getMessage());
            rate = 0;
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            rate = 0;
        }

        return rate;
    }
}
