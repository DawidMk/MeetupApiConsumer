package pl.dma.apiconsumer.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dma.apiconsumer.collectors.EventCollector;
import pl.dma.apiconsumer.events.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static pl.dma.apiconsumer.mappers.EventMapper.mapLinkedHashMapToEvent;

@Controller
public class IndexController {

    @Autowired
    private EventCollector eventCollector;

    @GetMapping("/")
    public String index(Model model) {
        //todo tworzy się linkedhashmap zamiast event
        ArrayList<LinkedHashMap<String, String>> eventsFromApi = eventCollector.getEventFromApi();
        List<Event> events = new ArrayList<>();
        for (LinkedHashMap<String, String> eventMap : eventsFromApi) {
            final Event event = mapLinkedHashMapToEvent(eventMap);
            events.add(event);
        }
        model.addAttribute("events", events);
        return "index";
    }

    @GetMapping("/filter")
    public String getEventSearchResult(@RequestParam(name = "from", required = false) String from, @RequestParam(name = "to", required = false) String to, Model model) {
        ArrayList<LinkedHashMap<String, String>> eventsFromApi = eventCollector.filterEventsFromApi(from, to);
        model.addAttribute("f", from);
        model.addAttribute("t", to);
        List<Event> events = new ArrayList<>();
        for (LinkedHashMap<String, String> eventMap : eventsFromApi) {
            final Event event = mapLinkedHashMapToEvent(eventMap);
            events.add(event);
//            }
        }
        model.addAttribute("events", events);
        return "index.html";
    }
}
