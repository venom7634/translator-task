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

    public static String processRequest(String text, String langPair){

        text = text.replaceAll("\r\n"," ");
        String[] words = text.split("\\s");
        ArrayList<String> arrayWords= new ArrayList<String>(Arrays.asList(words));
        arrayWords.removeAll(Arrays.asList("", null));

        ConfigThreads conf = new ConfigThreads();
        int n = conf.getCountWord() + conf.getMaxCountThreads();

//        TranslationProcessing tran = new TranslationProcessing();
//        String test = tran.maxCountThreads;

        int countThreads = 1;
        int max = 4;

        for (int i = 1; i < max+1;i++){
            if(arrayWords.size()/i < 50){
                countThreads = i;
                break;
            }
            countThreads = max;
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
            threads[i-1] = new RequestHandler(arrays[i-1],langPair);
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