package mikhail_ryazanov.translator_task.translator;

import mikhail_ryazanov.translator_task.translator.work_with_yandex.TranslationProcessing;



public class Transfer {

    private String transfer;

    public String getTransfer() {

        return transfer;
    }

    public Transfer(String text, String inLang, String outLang){
        this.transfer = TranslationProcessing.processRequest(text,inLang+"-"+outLang).trim();
    }
}
