package de.marcbellmann.football;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.marcbellmann.football.bet.BetService;
import de.marcbellmann.football.bet.Odd;
import de.marcbellmann.football.bet.Result;
import de.marcbellmann.football.data.Competition;
import de.marcbellmann.football.data.DataService;
import de.marcbellmann.football.data.Fixture;
import de.marcbellmann.football.data.FixtureDetails;
import de.marcbellmann.football.data.Fixtures;
import de.marcbellmann.football.data.LeagueTable;
import de.marcbellmann.football.data.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Marc Bellmann &lt;marc.bellmann@planyourtrip.travel&gt;
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private DataService dataService;

    @Autowired
    private BetService betService;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final List<Odd> odds = new ArrayList<>();
        final Fixtures nextFixtures = this.dataService.findNextFixtures();

        Competition competition = null;
        LeagueTable leagueTable = null;
        for (final Fixture fixture : nextFixtures.getFixtures()) {
            final FixtureDetails fixtureDetails = this.dataService.findFixtureDetails(fixture);
            final Team homeTeam = this.dataService.findHomeTeam(fixture);
            final Team awayTeam = this.dataService.findAwayTeam(fixture);
            if (competition == null) {
                competition = this.dataService.findCompetition(fixture);
            }
            if (leagueTable == null) {
                leagueTable = this.dataService.findLeagueTable(competition);
            }
            final Fixtures homeTeamFixtures = this.dataService.findFixtureOfTeam(homeTeam);
            final Fixtures awayTeamFixtures = this.dataService.findFixtureOfTeam(awayTeam);

            final Odd odd = this.betService.guessOdd(
                    fixtureDetails,
                    homeTeam,
                    awayTeam,
                    competition,
                    leagueTable,
                    homeTeamFixtures,
                    awayTeamFixtures
            );

            odds.add(odd);
        }

        final List<Result> results = this.betService.guessResults(odds);

        for (final Result result : results) {
            logger.info(result.toString());
        }

    }
}
