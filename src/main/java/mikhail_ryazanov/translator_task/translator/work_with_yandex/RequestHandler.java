package mikhail_ryazanov.translator_task.translator.work_with_yandex;

import java.util.List;

public class RequestHandler implements Runnable {

    private String langPair;
    private List<String> words;
    private String key;
    private String url;

    public Thread t;

    public RequestHandler(List<String> words,String langPair, String url, String key){
        this.langPair = langPair;
        this.words = words;
        this.t = new Thread(this);
        this.key = key;
        this.url = url;
    }

    @Override
    public void run() {
        for (int i =0; i < words.size();i++){
            words.set(i,YandexQueryTask.yandexQuery(words.get(i),langPair,url,key));
        }
    }

}
