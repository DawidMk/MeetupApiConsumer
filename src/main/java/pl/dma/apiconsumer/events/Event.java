package pl.dma.apiconsumer.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;

@JsonIgnoreProperties
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Event {

    private long id;
    private String eventName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
