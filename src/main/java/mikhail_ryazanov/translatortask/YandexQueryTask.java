package mikhail_ryazanov.translatortask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class YandexQueryTask {

    protected String yandexQuery(String text, String lang) {
        String textToBeTranslated = text;
        String languagePair = lang;
        String jsonString;

        try {
            String yandexKey = "trnsl.1.1.20180611T163054Z.35a803e32c109844.d7830b4899252825916b270247f2078bf93815c8";
            String yandexUrl = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + yandexKey
                    + "&text=" + textToBeTranslated + "&lang=" + languagePair;
            URL yandexTranslateURL = new URL(yandexUrl);

            HttpURLConnection httpConnection = (HttpURLConnection) yandexTranslateURL.openConnection();
            InputStream inputStream = httpConnection.getInputStream();
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuilder = new StringBuilder();
            while ((jsonString = buffReader.readLine()) != null) {
                stringBuilder.append(jsonString + "\n");
            }

            buffReader.close();
            inputStream.close();
            httpConnection.disconnect();

            String resultString = stringBuilder.toString().trim();
            resultString = resultString.substring(resultString.indexOf('[')+1);
            resultString = resultString.substring(0,resultString.indexOf("]"));
            resultString = resultString.substring(resultString.indexOf("\"")+1);
            resultString = resultString.substring(0,resultString.indexOf("\""));

            return resultString;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
