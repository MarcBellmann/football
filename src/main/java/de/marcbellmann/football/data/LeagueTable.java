package de.marcbellmann.football.data;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Marc Bellmann &lt;marc.bellmann@planyourtrip.travel&gt;
 */
public class LeagueTable {
    private String leagueCaption;
    private Integer matchday;
    private List<Standing> standing;

    @JsonProperty("_links")
    private Links links;

    public String getLeagueCaption() {
        return leagueCaption;
    }

    public Integer getMatchday() {
        return matchday;
    }

    public List<Standing> getPosition() {
        return standing;
    }

    public List<Standing> getStanding() {
        return standing;
    }

    public Integer getPosition(final String teamName) {
        for (final Standing team : standing) {
            if (Objects.equals(team.getTeamName(), teamName)) {
                return team.getPosition();
            }
        }
        throw new IllegalStateException("Team in Tabelle nicht gefunden");
    }

    String getLinkSelf() {
        return links.getSelf();
    }

    @Override
    public String toString() {
        return leagueCaption + " #" + matchday;
    }
}
