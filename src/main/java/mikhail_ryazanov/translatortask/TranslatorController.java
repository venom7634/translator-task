package mikhail_ryazanov.translatortask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@RestController
@RequestMapping("/")
public class TranslatorController {

    @Autowired
    WorkWithDataBase workWithDataBase;

   @GetMapping("/translate")
    public Transfer translate(HttpServletRequest request, @RequestParam("text") String text, @RequestParam("from") String from, @RequestParam("to") String to){
       addVisitInDB(new Date().toString(),request.getRemoteAddr(),text,from,to);
       Transfer tran = new Transfer(text,from,to);
       return tran;
    }

    @GetMapping("/visits")
    public Iterable<Visit> checkVisit(){
       Iterable<Visit> visits = workWithDataBase.getAllVisits();

       return visits;
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
