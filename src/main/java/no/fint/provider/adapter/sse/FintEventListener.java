package no.fint.provider.adapter.sse;

import lombok.extern.slf4j.Slf4j;
import no.fint.event.model.DefaultActions;
import no.fint.event.model.Event;
import no.fint.provider.health.service.EventHandlerService;
import no.fint.sse.AbstractEventListener;

@Slf4j
public class FintEventListener extends AbstractEventListener {

    private String orgId;
    private EventHandlerService eventHandler;

    public FintEventListener(EventHandlerService eventHandler, String orgId) {
        this.orgId = orgId;
        this.eventHandler = eventHandler;

        addAction(DefaultActions.HEALTH);
    }

    @Override
    public void onEvent(Event event) {
        if (event.getOrgId() != null && event.getOrgId().equals(orgId)) {
            log.info("EventListener for {}", event.getOrgId());
            log.info("Processing event: {}, for orgId: {}, for client: {}, action: {}",
                    event.getCorrId(),
                    event.getOrgId(),
                    event.getClient(),
                    event.getAction());

            if (event.isHealthCheck()) {
                eventHandler.postHealthCheckResponse(event);
            }
        } else {
            log.info("This is not EventListener for {}", event.getOrgId());
        }
    }
}
