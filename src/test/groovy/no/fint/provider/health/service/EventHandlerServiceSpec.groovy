package no.fint.provider.health.service

import no.fint.event.model.Event
import no.fint.provider.adapter.event.EventResponseService
import spock.lang.Specification

class EventHandlerServiceSpec extends Specification {
    private EventHandlerService eventHandlerService
    private EventResponseService eventResponseService

    void setup() {
        eventResponseService = Mock(EventResponseService)
        eventHandlerService = new EventHandlerService(eventResponseService: eventResponseService)
    }

    def "Post response on health check"() {
        given:
        def event = new Event('rogfk.no', 'test', 'HEALTH', 'test')

        when:
        eventHandlerService.postHealthCheckResponse(event)

        then:
        1 * eventResponseService.postResponse(_ as Event)
    }
}
