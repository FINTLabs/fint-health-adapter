package no.fint.provider.adapter.sse

import no.fint.event.model.DefaultActions
import no.fint.event.model.Event
import no.fint.provider.health.service.EventHandlerService
import org.glassfish.jersey.media.sse.InboundEvent
import spock.lang.Specification

class HealthEventListenerSpec extends Specification {
    private HealthEventListener listener
    private EventHandlerService eventHandlerService
    private InboundEvent inboundEvent

    void setup() {
        inboundEvent = Mock(InboundEvent)
        eventHandlerService = Mock(EventHandlerService)
        listener = new HealthEventListener(eventHandlerService, 'rogfk.no')
    }

    def "Handle event"() {
        given:
        def event = new Event(corrId: 'c978c986-8d50-496f-8afd-8d27bd68049b', action: DefaultActions.HEALTH.name(), orgId: 'rogfk.no', client: 'client')

        when:
        listener.onEvent(event)

        then:
        1 * eventHandlerService.postHealthCheckResponse(event)
    }
}
