package no.fint.provider.health.service;

import lombok.extern.slf4j.Slf4j;
import no.fint.event.model.Event;
import no.fint.event.model.Status;
import no.fint.event.model.health.Health;
import no.fint.event.model.health.HealthStatus;
import no.fint.provider.adapter.event.EventResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventHandlerService {

    @Autowired
    private EventResponseService eventResponseService;

    public void postHealthCheckResponse(Event event) {
        Event<Health> healthCheckEvent = new Event<>(event);
        healthCheckEvent.setStatus(Status.TEMP_UPSTREAM_QUEUE);
        healthCheckEvent.addData(new Health("adapter", HealthStatus.APPLICATION_HEALTHY.name()));
        eventResponseService.postResponse(healthCheckEvent);
    }

    public void rejectEvent(Event event) {
        event.setStatus(Status.ADAPTER_REJECTED);
        eventResponseService.postResponse(event);
    }

}
