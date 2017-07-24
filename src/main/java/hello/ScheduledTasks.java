package hello;

import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private static final StatsDClient statsd = new NonBlockingStatsDClient(
        "",                          /* prefix to any stats; may be null or empty string */
        "localhost",                        /* common case: localhost */
        8125                                 /* port */
    );

    @Scheduled(fixedRate = 5000)
    public void monitoring() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        statsd.incrementCounter("loop", "tag:test", "dogstatsd:true");
    }
}
