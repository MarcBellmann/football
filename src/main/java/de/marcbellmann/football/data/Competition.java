package de.marcbellmann.football.data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Marc Bellmann &lt;marc.bellmann@planyourtrip.travel&gt;
 */
public class Competition {

    private Long id;
    private String caption;
    private String league;
    private String year;
    private Integer currentMatchday;
    private Integer numberOfMatchdays;
    private Integer numberOfTeams;
    private Integer numberOfGames;
    private Date lastUpdated;

    @JsonProperty("_links")
    private Links links;

    public Long getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public String getLeague() {
        return league;
    }

    public String getYear() {
        return year;
    }

    public Integer getCurrentMatchday() {
        return currentMatchday;
    }

    public Integer getNumberOfMatchdays() {
        return numberOfMatchdays;
    }

    public Integer getNumberOfTeams() {
        return numberOfTeams;
    }

    public Integer getNumberOfGames() {
        return numberOfGames;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    String getLinkSelf() {
        return links.getSelf();
    }

    String getLinkTeams() {
        return links.getTeams();
    }

    String getLinkFixtures() {
        return links.getFixtures();
    }
    String getLinkLeagueTable() {
        return links.getLeagueTable();
    }
}
