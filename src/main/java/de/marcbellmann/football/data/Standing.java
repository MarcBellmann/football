package de.marcbellmann.football.data;

/**
 * @author Marc Bellmann &lt;marc.bellmann@planyourtrip.travel&gt;
 */
class Standing {

    private Integer position;
    private String teamName;
    private Integer playedGames;
    private String crestURI;
    private Integer points;
    private Integer goals;
    private Integer goalsAgainst;
    private Integer goalDifference;

    public Integer getPosition() {
        return position;
    }

    public String getTeamName() {
        return teamName;
    }

    public Integer getPlayedGames() {
        return playedGames;
    }

    public String getCrestURI() {
        return crestURI;
    }

    public Integer getPoints() {
        return points;
    }

    public Integer getGoals() {
        return goals;
    }

    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public Integer getGoalDifference() {
        return goalDifference;
    }

    @Override
    public String toString() {
        return position + ". " + teamName;
    }
}
