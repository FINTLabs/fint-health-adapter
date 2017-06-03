package no.fint.provider.health.service;

import lombok.extern.slf4j.Slf4j;
import no.fint.event.model.Event;
import no.fint.event.model.EventUtil;
import no.fint.event.model.Status;
import no.fint.event.model.health.Health;
import no.fint.event.model.health.HealthStatus;
import no.fint.model.relation.FintResource;
import no.fint.model.relation.Relation;
import no.fint.provider.adapter.event.EventResponseService;
import no.fint.provider.adapter.event.EventStatusService;
import no.fint.provider.health.Action;
import no.fint.pwfa.model.Dog;
import no.fint.pwfa.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EventHandlerService {

    @Autowired
    private EventResponseService eventResponseService;

    @Autowired
    private EventStatusService eventStatusService;


    public void handleEvent(String json) {
        Event event = EventUtil.toEvent(json);
        if (event.isHealthCheck()) {
            postHealthCheckResponse(event);
        }
    }

    public void postHealthCheckResponse(Event event) {
        Event<Health> healthCheckEvent = new Event<>(event);
        healthCheckEvent.setStatus(Status.TEMP_UPSTREAM_QUEUE);

        if (healthCheck()) {
            healthCheckEvent.addData(new Health("adapter", HealthStatus.APPLICATION_HEALTHY.name()));
        } else {
            healthCheckEvent.addData(new Health("adapter", HealthStatus.APPLICATION_UNHEALTHY));
            healthCheckEvent.setMessage("The adapter is unable to communicate with the application.");
        }

        eventResponseService.postResponse(healthCheckEvent);
    }

    private boolean healthCheck() {
        /*
         * Check application connectivity etc.
         */
        return true;
    }

}
