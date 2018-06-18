package mikhail_ryazanov.translator_task.translator;

import mikhail_ryazanov.translator_task.translator.work_with_yandex.RequestHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class TranslationProcessing {

    @Value("${threads.max.count}")
    private int maxCountThreads;

    @Value("${yandex.key}")
    private String yandexKey;

    @Value("${yandex.url}")
    private String yandexUrl;

    public String processRequest(String text, String langPair){

        text = text.replaceAll("\r\n"," ");
        String[] words = text.split("\\s");
        ArrayList<String> arrayWords= new ArrayList<String>(Arrays.asList(words));
        arrayWords.removeAll(Arrays.asList("", null));

        ExecutorService executor = Executors.newFixedThreadPool(maxCountThreads);
        ArrayList<Future<String>> futures = new ArrayList<Future<String>>();

        for(String word :words){
            RequestHandler callable = new RequestHandler(word,langPair,yandexKey,yandexUrl);
            Future<String> future = executor.submit(callable);
            futures.add(future);
        }
        String result = "";
        for(Future fut: futures){
            try {
                result+= fut.get()+" ";
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        return result;
    }

}
