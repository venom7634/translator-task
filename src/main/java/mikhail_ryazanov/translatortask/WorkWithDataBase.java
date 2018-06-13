package mikhail_ryazanov.translatortask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class WorkWithDataBase {

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    final private JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkWithDataBase(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcTemplate.execute("DROP TABLE IF EXISTS visits ");
        this.jdbcTemplate.execute("CREATE TABLE visits(" +
                "id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "timeQuery NCHAR(60)," +
                "IPClient NCHAR(15)," +
                "text NCHAR(500)," +
                "langIn NCHAR(3)," +
                "langOut NCHAR(3))");
    }

    public void addVisitInDB(Visit visit){
        String query = "INSERT INTO visits " +
                "(timeQuery, IPClient,text,langIn,langOut) VALUES (?, ?, ?, ?, ?)";

        this.jdbcTemplate.update(query, new Object[] {visit.getTimeQuery(),
                visit.getIPClient(),visit.getText(),visit.getLangIn(),visit.getLangOut()});
    }

    public ArrayList<Visit> getAllVisits(){
        String sql = "SELECT timeQuery, IPClient,text,langIn,langOut FROM visits";

        ArrayList<Visit> visits = new ArrayList<Visit>();

        List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql);

        for (Map row : rows) {
            Visit visit = new Visit();

            visit.setIPClient((String)row.get("IPClient"));
            visit.setLangIn((String)row.get("langIn"));
            visit.setLangOut((String)row.get("langOut"));
            visit.setText((String)row.get("text"));
            visit.setTimeQuery((String)row.get("timeQuery"));

            visits.add(visit);
        }

        return visits;
    }
}
