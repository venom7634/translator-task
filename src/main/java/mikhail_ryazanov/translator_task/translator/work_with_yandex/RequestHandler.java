package mikhail_ryazanov.translator_task.translator.work_with_yandex;

import java.util.List;

public class RequestHandler implements Runnable {

    public String langPair;
    public List<String> words;
    public Thread t;

    public RequestHandler(List<String> words,String langPair){
        this.langPair = langPair;
        this.words = words;
        this.t = new Thread(this);
    }


    @Override
    public void run() {
        for (int i =0; i < words.size();i++){
            words.set(i,YandexQueryTask.yandexQuery(words.get(i),langPair));
        }
    }

}
