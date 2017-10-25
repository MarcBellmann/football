package de.marcbellmann.football.bet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import de.marcbellmann.football.data.Competition;
import de.marcbellmann.football.data.Fixture;
import de.marcbellmann.football.data.FixtureDetails;
import de.marcbellmann.football.data.Fixtures;
import de.marcbellmann.football.data.Head2Head;
import de.marcbellmann.football.data.LeagueTable;
import de.marcbellmann.football.data.Team;

/**
 * @author Marc Bellmann &lt;marc.bellmann@planyourtrip.travel&gt;
 */
@Service
public class BetService {

    //    private static final BigDecimal MARKTWERT_2_BUNDESLIGA = new BigDecimal(266800000);
    private static final BigDecimal[] LETZTE_SPIELE_GEWONNEN = new BigDecimal[]{
            new BigDecimal(40),
            new BigDecimal(30),
            new BigDecimal(20),
            new BigDecimal(10),
            new BigDecimal(0)
    };
    private static final BigDecimal MULTIPLIKATOR_POSITION_INDEX = BigDecimal.TEN;
    private static final BigDecimal MULTIPLIKATOR_HOME_GAME_INDEX = new BigDecimal(50);
    private static final BigDecimal MULTIPLIKATOR_LAST_MATCH_INDEX = new BigDecimal(25);

    public Odd guessOdd(
            final FixtureDetails fixture,
            final Team homeTeam,
            final Team awayTeam,
            final Competition competition,
            final LeagueTable leagueTable,
            final Fixtures homeTeamFixtures,
            final Fixtures awayTeamFixtures) {

        final BigDecimal homeTeamOdd = bet(fixture, homeTeam, competition, leagueTable, homeTeamFixtures);
        final BigDecimal awayTeamOdd = bet(fixture, awayTeam, competition, leagueTable, awayTeamFixtures);

        return new Odd(
                homeTeam.getName(),
                awayTeam.getName(),
                homeTeamOdd,
                awayTeamOdd
        );
    }

    private BigDecimal bet(
            final FixtureDetails fixture,
            final Team team,
            final Competition competition,
            final LeagueTable leagueTable,
            final Fixtures teamFixtures) {

        final BigDecimal positionIndex = calcPositionIndex(team, competition, leagueTable);
        final BigDecimal homeGameIndex = calcHomeGameIndex(team, fixture);
        final BigDecimal lastFixturesIndex = calcLastFixturesIndex(team, teamFixtures);
//        final BigDecimal marketValueIndex = calcMarketValueIndex(team);
        final BigDecimal lastMatchIndex = calcLastMatchIndex(team, fixture);

        return positionIndex
                .add(homeGameIndex)
                .add(lastFixturesIndex)
//                .add(marketValueIndex)
                .add(lastMatchIndex);
    }

    private static BigDecimal calcPositionIndex(final Team team, final Competition competition, final LeagueTable leagueTable) {
        final Integer position = leagueTable.getPosition(team.getName());

        return new BigDecimal(competition.getNumberOfTeams() - position)
                .multiply(MULTIPLIKATOR_POSITION_INDEX);
    }

    private static BigDecimal calcHomeGameIndex(final Team team, final FixtureDetails fixtureDetails) {
        final Fixture fixture = fixtureDetails.getFixture();

        if (team == null || team.getName() == null || fixture == null || fixture.getHomeTeamName() == null) {
            return BigDecimal.ZERO;
        }

        return (team.getName().equals(fixture.getHomeTeamName()) ? BigDecimal.ONE : BigDecimal.ZERO)
                .multiply(MULTIPLIKATOR_HOME_GAME_INDEX);
    }

    private static BigDecimal calcLastFixturesIndex(final Team team, final Fixtures teamFixtures) {
        final List<Fixture> last5Fixtures = teamFixtures.getLast5Fixtures();
        BigDecimal index = BigDecimal.ZERO;

        for (int i = 0; i < last5Fixtures.size() && i < 5; i++) {
            final Fixture fixture = last5Fixtures.get(i);
            if (fixture.hatTeamGewonnen(team)) {
                index = index.add(LETZTE_SPIELE_GEWONNEN[i]);
            }
        }

        return index;
    }

//    private static BigDecimal calcMarketValueIndex(final Team team) {
//        final BigDecimal marketValue = team.getSquadMarketValue();
//        return marketValue == null ? BigDecimal.ZERO
//                : marketValue.divide(MARKTWERT_2_BUNDESLIGA, BigDecimal.ROUND_UP, 0);
//    }

    private static BigDecimal calcLastMatchIndex(final Team team, final FixtureDetails fixture) {
        final Head2Head head2head = fixture.getHead2head();
        if (head2head.getFixtures().isEmpty()) {
            return BigDecimal.ZERO;
        }
        final Fixture letzteBegenung = head2head.getFixtures().get(0);
        return (letzteBegenung.hatTeamGewonnen(team) ? BigDecimal.ONE : BigDecimal.ZERO)
                .multiply(MULTIPLIKATOR_LAST_MATCH_INDEX);
    }

    public List<Result> guessResults(final List<Odd> odds) {
        final List<Result> results = new ArrayList<>();
        for (final Odd odd : odds) {
            final Result result = guessResult(odd);

            results.add(result);
        }
        return results;
    }

    private Result guessResult(final Odd odd) {
        final int oddDifferenz = odd.getDifferenz().intValue();
        final Result result = new Result(
                odd
        );

        if (oddDifferenz > -50 && oddDifferenz < 50) {
            if (oddDifferenz > -25 && oddDifferenz < 25) {
                result.setResult(0, 0);
            } else {
                result.setResult(1, 1);
            }
        } else if (oddDifferenz > 0) {
            // Heimsieg
            if (oddDifferenz < 100) {
                result.setResult(1, 0);
            } else if (oddDifferenz < 150) {
                result.setResult(2, 0);
            } else if (oddDifferenz >= 150) {
                result.setResult(3, 1);
            }
        } else {
            // Auswärtssieg
            if (oddDifferenz > -100) {
                result.setResult(0, 1);
            } else if (oddDifferenz > -150) {
                result.setResult(0, 2);
            } else if (oddDifferenz <= -150) {
                result.setResult(1, 3);
            }
        }
        return result;
    }
}
