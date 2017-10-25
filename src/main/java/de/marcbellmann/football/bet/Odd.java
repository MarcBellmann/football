package de.marcbellmann.football.bet;

import java.math.BigDecimal;

/**
 * @author Marc Bellmann &lt;marc.bellmann@planyourtrip.travel&gt;
 */
public class Odd {

    private final String homeTeamName;
    private final String awayTeamName;
    private final BigDecimal homeTeamOdd;
    private final BigDecimal awayTeamOdd;

    Odd(final String homeTeamName, final String awayTeamName, final BigDecimal homeTeamOdd, final BigDecimal awayTeamOdd) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeTeamOdd = homeTeamOdd;
        this.awayTeamOdd = awayTeamOdd;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public BigDecimal getHomeTeamOdd() {
        return homeTeamOdd;
    }

    public BigDecimal getAwayTeamOdd() {
        return awayTeamOdd;
    }

    BigDecimal getDifferenz() {
        return homeTeamOdd.subtract(awayTeamOdd);
    }

    @Override
    public String toString() {
        return homeTeamOdd + " : " + awayTeamOdd + "\t" + getDifferenz() + "\t" + homeTeamName + " : " + awayTeamName;
    }
}
