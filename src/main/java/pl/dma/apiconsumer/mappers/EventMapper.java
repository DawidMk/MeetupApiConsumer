package pl.dma.apiconsumer.mappers;

import lombok.Data;
import pl.dma.apiconsumer.events.Event;

import java.time.LocalDate;
import java.util.LinkedHashMap;

@Data
public class EventMapper {

    public static Event mapLinkedHashMapToEvent(LinkedHashMap<String, String> inputMap) {
        return Event.builder()
//                .id(inputMap.get("id"))
                .eventName(inputMap.get("eventName"))
                .description(inputMap.get("description"))
                .startDate(LocalDate.parse(inputMap.get("startDate")))
                .endDate(LocalDate.parse(inputMap.get("endDate")))
                .build();
    }
}
