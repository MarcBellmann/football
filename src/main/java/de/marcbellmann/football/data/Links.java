package de.marcbellmann.football.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Marc Bellmann &lt;marc.bellmann@planyourtrip.travel&gt;
 */
class Links {

    @JsonProperty("self")
    private Href self;

    @JsonProperty("competition")
    private Href competition;

    @JsonProperty("homeTeam")
    private Href homeTeam;

    @JsonProperty("awayTeam")
    private Href awayTeam;

    @JsonProperty("fixtures")
    private Href fixtures;

    @JsonProperty("players")
    private Href players;

    @JsonProperty("teams")
    private Href teams;

    @JsonProperty("leagueTable")
    private Href leagueTable;

    String getSelf() {
        return self.getHref();
    }

    String getCompetition() {
        return competition.getHref();
    }

    String getHomeTeam() {
        return homeTeam.getHref();
    }

    String getAwayTeam() {
        return awayTeam.getHref();
    }

    String getFixtures() {
        return fixtures.getHref();
    }

    String getPlayers() {
        return players.getHref();
    }

    String getTeams() {
        return teams.getHref();
    }

    String getLeagueTable() {
        return leagueTable.getHref();
    }
}
