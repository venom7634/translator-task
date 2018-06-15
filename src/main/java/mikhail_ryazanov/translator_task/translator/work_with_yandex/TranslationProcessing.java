package mikhail_ryazanov.translator_task.translator.work_with_yandex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TranslationProcessing {

    @Value("${threads.max.count}")
    private int maxCountThreads;

    @Value("${threads.count.words}")
    private int countWords;

    @Value("${yandex.key}")
    private String yandexKey;

    @Value("${yandex.url}")
    private String yandexUrl;

    public String processRequest(String text, String langPair){

        text = text.replaceAll("\r\n"," ");
        String[] words = text.split("\\s");
        ArrayList<String> arrayWords= new ArrayList<String>(Arrays.asList(words));
        arrayWords.removeAll(Arrays.asList("", null));

        int countThreads = 1;

        for (int i = 1; i < maxCountThreads+1;i++){
            if(arrayWords.size()/i < countWords){
                countThreads = i;
                break;
            }
            countThreads = maxCountThreads;
        }

        RequestHandler[] threads = new RequestHandler[countThreads];
        List<String>[] arrays = new List[countThreads];
        int step = (int) Math.ceil(((double)arrayWords.size())/countThreads);
        for (int i = 1; i<countThreads+1;i++){
            int startValue = step*(i-1);
            int endValue = step*i;

            if(endValue > arrayWords.size())
                endValue=arrayWords.size();
            arrays[i-1] = arrayWords.subList(startValue,endValue);
            threads[i-1] = new RequestHandler(arrays[i-1],langPair,yandexUrl,yandexKey);
            threads[i-1].t.start();
        }

        for (int i = 1; i<countThreads+1;i++){
            try {
                threads[i-1].t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String result = "";
        for (int i = 1; i<countThreads+1;i++){
            for(String word :arrays[i-1]){
                result+=word+" ";
            }
        }
        return result;
    }

}
