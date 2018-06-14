package mikhail_ryazanov.translator_task.translator.work_with_yandex;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class YandexQueryTask {

    protected String yandexQuery(String text, String lang) {
        String textToBeTranslated = text;
        String languagePair = lang;
        String jsonString;
        FileInputStream file;
        Properties property = new Properties();

        try {
            file = new FileInputStream("src/main/resources/yandex.properties");
            property.load(file);

            String yandexUrl = property.getProperty("yandexUrl") + property.getProperty("yandexKey")
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
