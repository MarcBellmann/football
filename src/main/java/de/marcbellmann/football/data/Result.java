package de.marcbellmann.football.data;

/**
 * @author Marc Bellmann &lt;marc.bellmann@planyourtrip.travel&gt;
 */
class Result {

    private Integer goalsHomeTeam;
    private Integer goalsAwayTeam;

//    private Result halfTime;
//    private Result extraTime;
//    private Result penaltyShootout;

    public Integer getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public Integer getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

//    public Result getHalfTime() {
//        return halfTime;
//    }
//
//    public Result getExtraTime() {
//        return extraTime;
//    }
//
//    public Result getPenaltyShootout() {
//        return penaltyShootout;
//    }

    public boolean isDraw() {
        return goalsHomeTeam == goalsAwayTeam;
    }

    public boolean isHomeWon() {
        return goalsHomeTeam > goalsAwayTeam;
    }

    public boolean isAwayWon() {
        return goalsHomeTeam < goalsAwayTeam;
    }

    @Override
    public String toString() {
        return goalsHomeTeam + " : " + goalsAwayTeam;
    }
}
