package mikhail_ryazanov.translator_task.translator.work_with_yandex;

import java.util.ArrayList;

public class RequestHandler implements Runnable {

    volatile public static String langPair;
    volatile public static int positionInArray;
    volatile public static ArrayList<String> arrayWord;
    public Thread t;

    public RequestHandler(){
        this.t = new Thread(this);
    }


    @Override
    public void run() {
        while (positionInArray < arrayWord.size()) {
            String word;
            int position;
            YandexQueryTask yqt = new YandexQueryTask();

            synchronized (RequestHandler.class) {
                word = arrayWord.get(positionInArray);
                position = positionInArray;
                positionInArray++;
            }

            word = yqt.yandexQuery(word, langPair);

            synchronized (RequestHandler.class) {
                arrayWord.set(position, word);
            }
        }
    }

}
