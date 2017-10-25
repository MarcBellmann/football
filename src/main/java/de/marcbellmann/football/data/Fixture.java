package de.marcbellmann.football.data;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Marc Bellmann &lt;marc.bellmann@planyourtrip.travel&gt;
 */
public class Fixture {

    private Date date;
    private String status;
    private Integer matchday;
    private String homeTeamName;
    private String awayTeamName;
    private Result result;

    @JsonProperty("_links")
    private Links links;

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public Integer getMatchday() {
        return matchday;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public Result getResult() {
        return result;
    }

    String getLinkSelf() {
        return links.getSelf();
    }

    String getLinkCompetition() {
        return links.getCompetition();
    }

    String getLinkHomeTeam() {
        return links.getHomeTeam();
    }

    String getLinkAwayTeam() {
        return links.getAwayTeam();
    }

    public Boolean isResultSet() {
        return result != null && result.getGoalsHomeTeam() != null && result.getGoalsAwayTeam() != null;
    }

    @Override
    public String toString() {
        return homeTeamName + " : " + awayTeamName;
    }

    public boolean hatTeamGewonnen(final Team team) {
        final String teamName = team.getName();
        if (result.isDraw()) {
            return false;
        } else if (Objects.equals(teamName, homeTeamName)) {
            return result.isHomeWon();
        } else if (Objects.equals(teamName, awayTeamName)) {
            return result.isAwayWon();
        }
        throw new IllegalStateException("Team [" + teamName
                + "] ist weder Heim [" + homeTeamName + "] noch Auwärtsteam [" + awayTeamName + "]");
    }
}
