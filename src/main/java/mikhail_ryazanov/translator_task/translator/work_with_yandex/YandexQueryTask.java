package mikhail_ryazanov.translator_task.translator.work_with_yandex;

import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.Properties;

class YandexQueryTask {

     static String yandexQuery(String text, String lang) {
        FileInputStream file;
        Properties property = new Properties();

        try {
            file = new FileInputStream("src/main/resources/yandex.properties");
            property.load(file);
            text = text.replaceAll("[@'\"$#№;%&*]","");
            String yandexUrl = property.getProperty("yandexUrl") + property.getProperty("yandexKey")
                    + "&text=" + text + "&lang=" + lang;

            RestTemplate restTemplate = new RestTemplate();
            String replyYandex = restTemplate.getForObject(yandexUrl, String.class);

            String resultString = replyYandex.trim();
            resultString = resultString.substring(resultString.indexOf('[')+1);
            resultString = resultString.substring(0,resultString.indexOf("]"));
            resultString = resultString.substring(resultString.indexOf("\"")+1);
            resultString = resultString.substring(0,resultString.indexOf("\""));

            return resultString;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
