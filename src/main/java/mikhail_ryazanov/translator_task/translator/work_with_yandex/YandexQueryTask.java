package mikhail_ryazanov.translator_task.translator.work_with_yandex;

import org.springframework.web.client.RestTemplate;

class YandexQueryTask {


    static public String yandexQuery(String text, String lang, String url, String key) {

            String yandexUrl = url + key
                    + "&text=" + text + "&lang=" + lang;

            RestTemplate restTemplate = new RestTemplate();
            String replyYandex = restTemplate.getForObject(yandexUrl, String.class);

            String resultString = replyYandex.trim();
            resultString = resultString.substring(resultString.indexOf('[')+1);
            resultString = resultString.substring(0,resultString.indexOf("]"));
            resultString = resultString.substring(resultString.indexOf("\"")+1);
            resultString = resultString.substring(0,resultString.indexOf("\""));

            return resultString;

    }
}
