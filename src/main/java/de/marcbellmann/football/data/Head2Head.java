package de.marcbellmann.football.data;

import java.util.Date;
import java.util.List;

/**
 * @author Marc Bellmann &lt;marc.bellmann@planyourtrip.travel&gt;
 */
public class Head2Head {

    private Integer count;
    private Date timeFrameStart;
    private Date timeFrameEnd;
    private Integer homeTeamWins;
    private Integer awayTeamWins;
    private Integer draws;
    private Fixture lastHomeWinHomeTeam;
    private Fixture lastWinHomeTeam;
    private Fixture lastAwayWinAwayTeam;
    private Fixture lastWinAwayTeam;
    private List<Fixture> fixtures;

    public Integer getCount() {
        return count;
    }

    public Date getTimeFrameStart() {
        return timeFrameStart;
    }

    public Date getTimeFrameEnd() {
        return timeFrameEnd;
    }

    public Integer getHomeTeamWins() {
        return homeTeamWins;
    }

    public Integer getAwayTeamWins() {
        return awayTeamWins;
    }

    public Integer getDraws() {
        return draws;
    }

    public Fixture getLastHomeWinHomeTeam() {
        return lastHomeWinHomeTeam;
    }

    public Fixture getLastWinHomeTeam() {
        return lastWinHomeTeam;
    }

    public Fixture getLastAwayWinAwayTeam() {
        return lastAwayWinAwayTeam;
    }

    public Fixture getLastWinAwayTeam() {
        return lastWinAwayTeam;
    }

    public List<Fixture> getFixtures() {
        return fixtures;
    }
}
