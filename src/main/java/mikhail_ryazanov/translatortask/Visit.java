package mikhail_ryazanov.translatortask;

public class Visit {
    private String timeQuery;
    private String IPClient;
    private String text;
    private String langIn;
    private String langOut;

    public Visit(){

    }

    public Visit(String timeQuery, String IPClient, String text, String langIn, String langOut) {
        this.timeQuery = timeQuery;
        this.IPClient = IPClient;
        this.text = text;
        this.langIn = langIn;
        this.langOut = langOut;
    }

    public String getTimeQuery() {

        return timeQuery;
    }

    public void setTimeQuery(String timeQuery) {

        this.timeQuery = timeQuery;
    }

    public String getIPClient() {

        return IPClient;
    }

    public void setIPClient(String IPClient) {

        this.IPClient = IPClient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {

        this.text = text;
    }

    public String getLangIn() {

        return langIn;
    }

    public void setLangIn(String langIn) {

        this.langIn = langIn;
    }

    public String getLangOut() {

        return langOut;
    }

    public void setLangOut(String langOut) {

        this.langOut = langOut;
    }
}
