package mikhail_ryazanov.translator_task.translator.work_with_yandex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class ConfigThreads {

        @Value("${threads.max.count}")
        private int maxCountThreads;

        @Value("${threads.count.words}")
        private int countWord;

    public int getCountWord() {
        return countWord;
    }

    public int getMaxCountThreads() {
        return maxCountThreads;
    }
}
