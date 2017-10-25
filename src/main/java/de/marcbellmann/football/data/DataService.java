package de.marcbellmann.football.data;

import java.util.Random;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Marc Bellmann &lt;marc.bellmann@planyourtrip.travel&gt;
 */
@Service
public class DataService {

    private static final String[] API_KEYS = new String[] {
            "ad88c4e574f845b4a9b7d0ddcc1393e3",
            "897517be28304db3975acbec8283b0df",
            "cad2b5c51ecc4e938641fdb0821df80e",
    };

    private final RestTemplate restTemplate;

    public DataService() {
        this.restTemplate = new RestTemplateBuilder().build();
    }


    public Fixtures findNextFixtures() {
//        return get("http://api.football-data.org/v1/fixtures?league=BL2&timeFrame=n11", Fixtures.class);
        return get("http://api.football-data.org/v1/fixtures?league=BL2", Fixtures.class);
    }

    public FixtureDetails findFixtureDetails(final Fixture fixture) {
        return get(fixture.getLinkSelf(), FixtureDetails.class);
    }

    public Team findHomeTeam(final Fixture fixture) {
        return get(fixture.getLinkHomeTeam(), Team.class);
    }

    public Team findAwayTeam(final Fixture fixture) {
        return get(fixture.getLinkAwayTeam(), Team.class);
    }

    public Competition findCompetition(final Fixture fixture) {
        return get(fixture.getLinkCompetition(), Competition.class);
    }

    public LeagueTable findLeagueTable(final Competition competition) {
        return get(competition.getLinkLeagueTable(), LeagueTable.class);
    }

    public Fixtures findFixtureOfTeam(final Team team) {
        return get(team.getLinkFixtures(), Fixtures.class);
    }

    private <T> T get(String url, Class<T> clazz) {
        final int idx = new Random().nextInt(API_KEYS.length);
        final String apiKey = API_KEYS[idx];

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Auth-Token", apiKey);
        final HttpEntity<?> requestEntity = new HttpEntity<>(httpHeaders);
        final ResponseEntity<T> exchange = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, clazz);
        return exchange.getBody();
    }
}
