package mikhail_ryazanov.translator_task.translator.work_with_yandex;

import org.springframework.web.client.RestTemplate;

class YandexQueryTask {


    static public String yandexQuery(String text, String lang, String url, String key) {
            text = text.replaceAll("[\"']","");
            String yandexUrl = url + key
                    + "&text=" + text + "&lang=" + lang;

            RestTemplate restTemplate = new RestTemplate();
            String replyYandex = restTemplate.getForObject(yandexUrl, String.class);

            replyYandex = replyYandex.substring(replyYandex.indexOf('[')+1);
            replyYandex = replyYandex.substring(0,replyYandex.indexOf("]"));
            replyYandex = replyYandex.substring(replyYandex.indexOf("\"")+1);
            replyYandex = replyYandex.substring(0,replyYandex.indexOf("\""));

            return replyYandex;

    }
}
