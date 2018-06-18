package mikhail_ryazanov.translator_task.translator.work_with_yandex;

import java.util.concurrent.Callable;

public class RequestHandler implements Callable<String> {

    private String word;
    private String langPair;
    private String key;
    private String url;

    public RequestHandler(String word, String langPair, String key, String url) {
        this.word = word;
        this.langPair = langPair;
        this.key = key;
        this.url = url;
    }

    @Override
    public String call() throws Exception {
        return YandexQueryTask.yandexQuery(word,langPair,url,key);
    }

}
