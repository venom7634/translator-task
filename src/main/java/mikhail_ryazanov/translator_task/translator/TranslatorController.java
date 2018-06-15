package mikhail_ryazanov.translator_task.translator;

import mikhail_ryazanov.translator_task.visit_to_translator.Visit;
import mikhail_ryazanov.translator_task.visit_to_translator.VisitsInDataBase;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/")
public class TranslatorController {

    @Autowired
    Transfer transfer;
    @Autowired
    VisitsInDataBase workWithDataBase;

   @GetMapping("/translate")
    public String translate(HttpServletRequest request, @RequestParam("text") String text, @RequestParam("from") String from, @RequestParam("to") String to){
       return transfer.translate(text,from,to);
    }

    @GetMapping("/visits")
    public Iterable<Visit> checkVisit(){
        return workWithDataBase.getAllVisits();
    }

    private void addVisitInDB(String timeQuery, String IPClient, String text, String langIn, String langOut){
        Visit visit = new Visit();
        visit.setIPClient(IPClient);
        visit.setLangIn(langIn);
        visit.setLangOut(langOut);
        visit.setText(text);
        visit.setTimeQuery(timeQuery);
        workWithDataBase.addVisitInDB(visit);
    }
}
