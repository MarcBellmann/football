package de.marcbellmann.football.bet;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * @author Marc Bellmann &lt;marc.bellmann@planyourtrip.travel&gt;
 */
public class BetServiceTest {

    private BetService betService;

    @Before
    public void setUp() throws Exception {
        this.betService = new BetService();
    }

    @Test
    public void testDraw() throws Exception {
        assertThat(result(200, 200), is(1, 1));
    }

    @Test
    public void testHomeWin() throws Exception {
        assertThat(result(210, 190), is(2, 1));
    }

    @Test
    public void testAwayWin() throws Exception {
        assertThat(result(190, 210), is(1, 2));
    }

    @Test
    public void testHomeWinGreat() throws Exception {
        assertThat(result(220, 180), is(3, 1));
    }

    @Test
    public void testAwayWinGreat() throws Exception {
        assertThat(result(180, 220), is(1, 3));
    }

    @Test
    public void testHomeWinGorgeous() throws Exception {
        assertThat(result(250, 150), is(4, 1));
    }

    @Test
    public void testAwayWinGorgeous() throws Exception {
        assertThat(result(150, 250), is(1, 4));
    }

    private Result result(final int homeTeamOdd, final int awayTeamOdd) {
        final List<Result> results = this.betService.guessResults(Arrays.asList(
                new Odd("Team 1", "Team 2", new BigDecimal(homeTeamOdd), new BigDecimal(awayTeamOdd))
        ));

        return results.get(0);
    }

    private static TypeSafeMatcher<Result> is(
            final Integer homeTeamResult,
            final Integer awayTeamResult) {

        return new TypeSafeMatcher<Result>() {
            @Override
            protected boolean matchesSafely(final Result item) {
                return homeTeamResult.equals(item.getHomeTeamResult()) && awayTeamResult.equals(item.getAwayTeamResult());
            }

            @Override
            public void describeTo(final Description description) {
            }
        };
    }
}