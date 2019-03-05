package no.fint.provider

import no.fint.oauth.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Ignore
import spock.lang.Specification

@Ignore
@ActiveProfiles('pwfa')
@SpringBootTest
class ConfigSpec extends Specification {

    @Autowired
    private TokenService tokenService

    def "Get access token"() {
        when:
        def token = tokenService.getAccessToken('https://play-with-fint-adapter.felleskomponent.no/provider/sse/auth-init')

        then:
        token != null
    }

}
