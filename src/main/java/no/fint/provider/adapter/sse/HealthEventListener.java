package no.fint.provider.adapter.sse;

import lombok.extern.slf4j.Slf4j;
import no.fint.event.model.Event;
import no.fint.provider.health.service.EventHandlerService;
import no.fint.sse.AbstractEventListener;

@Slf4j
public class HealthEventListener extends AbstractEventListener {
    private EventHandlerService eventHandler;

    public HealthEventListener(EventHandlerService eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void onEvent(Event event) {
        if (event.isHealthCheck()) {
            log.info("Processing event: {}, for orgId: {}, for client: {}, action: {}",
                    event.getCorrId(),
                    event.getOrgId(),
                    event.getClient(),
                    event.getAction());

            eventHandler.postHealthCheckResponse(event);
        } else {
            log.info("Rejecting event: {}", event);
            eventHandler.rejectEvent(event);
        }
    }
}
