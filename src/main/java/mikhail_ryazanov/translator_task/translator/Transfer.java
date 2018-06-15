package mikhail_ryazanov.translator_task.translator;

import mikhail_ryazanov.translator_task.translator.work_with_yandex.TranslationProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Transfer {

    @Autowired
    private TranslationProcessing translationProcessing;

    public String translate(String text, String inLang, String outLang){
        return translationProcessing.processRequest(text,inLang+"-"+outLang).trim();
    }

}
