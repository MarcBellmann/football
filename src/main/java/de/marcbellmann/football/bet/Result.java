package de.marcbellmann.football.bet;

import java.math.BigDecimal;

/**
 * @author Marc Bellmann &lt;marc.bellmann@planyourtrip.travel&gt;
 */
public class Result {

    private final Odd odd;

    private Integer homeTeamResult;
    private Integer awayTeamResult;

    Result(final Odd odd) {

        this.odd = odd;
    }

    public String getHomeTeamName() {
        return odd.getHomeTeamName();
    }

    public String getAwayTeamName() {
        return odd.getAwayTeamName();
    }

    public BigDecimal getHomeTeamOdd() {
        return odd.getHomeTeamOdd();
    }

    public BigDecimal getAwayTeamOdd() {
        return odd.getAwayTeamOdd();
    }

    public BigDecimal getDifferenz() {
        return odd.getDifferenz();
    }

    void setResult(final Integer homeTeamResult, final Integer awayTeamResult) {
        this.homeTeamResult = homeTeamResult;
        this.awayTeamResult = awayTeamResult;
    }

    public Integer getHomeTeamResult() {
        return homeTeamResult;
    }

    public Integer getAwayTeamResult() {
        return awayTeamResult;
    }

    @Override
    public String toString() {
        return homeTeamResult + " : " + awayTeamResult + "\t" + getHomeTeamOdd() + " : " + getAwayTeamOdd() + "\t" + getHomeTeamName() + " : " + getAwayTeamName();
    }
}

