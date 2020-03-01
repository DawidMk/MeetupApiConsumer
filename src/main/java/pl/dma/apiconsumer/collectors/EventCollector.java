package pl.dma.apiconsumer.collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.dma.apiconsumer.events.Event;

import java.util.ArrayList;

@Service
@Slf4j
public class EventCollector {

    @Autowired
    RestTemplate restTemplate;

    public ArrayList getEventFromApi() {
        ResponseEntity<ArrayList> responseEntity =
                restTemplate.getForEntity("http://dell-komputer:8080/api/events", ArrayList.class);
        return responseEntity.getBody();
    }

    public ArrayList filterEventsFromApi(String from, String to) {
        if (from.equals("") || to.equals("")) {
            return getEventFromApi();
        } else {
            ResponseEntity<ArrayList> responseEntity =
                    restTemplate.getForEntity("http://dell-komputer:8080/api/filter?from=" + from + "&to=" + to, ArrayList.class);
            return responseEntity.getBody();
        }
    }
}
