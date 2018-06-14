package mikhail_ryazanov.translator_task.translator;

import mikhail_ryazanov.translator_task.translator.work_with_yandex.RequestHandler;

import java.util.ArrayList;
import java.util.Arrays;


public class Transfer {

    private String transfer;

    public String getTransfer() {
        return transfer;
    }

    public Transfer(String text, String inLang, String outLang){
        text = text.replaceAll("\r\n"," ");
        String[] words = text.split("\\s");
        ArrayList<String>  arrayWords= new ArrayList<String>(Arrays.asList(words));
        arrayWords.removeAll(Arrays.asList("", null));

        RequestHandler.arrayWord = arrayWords;
        RequestHandler.positionInArray = 0;
        RequestHandler.langPair = inLang+"-"+outLang;

        RequestHandler threadOne = new RequestHandler();
        RequestHandler threadTwo = new RequestHandler();
        RequestHandler threadThree = new RequestHandler();

        threadOne.t.start();
        threadTwo.t.start();
        threadThree.t.start();

        try {
            threadOne.t.join();
            threadTwo.t.join();
            threadThree.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String resultString = "";
        for(String word:RequestHandler.arrayWord){
            resultString+=word+" ";
        }

        this.transfer = resultString.trim();
    }
}
