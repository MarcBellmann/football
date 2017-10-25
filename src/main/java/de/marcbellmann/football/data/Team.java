package de.marcbellmann.football.data;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Marc Bellmann &lt;marc.bellmann@planyourtrip.travel&gt;
 */
public class Team {

    private String name;
    private String code;
    private String shortName;
    private BigDecimal squadMarketValue;
    private String crestUrl;

    @JsonProperty("_links")
    private Links links;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getShortName() {
        return shortName;
    }

    public BigDecimal getSquadMarketValue() {
        return squadMarketValue;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    String getLinkSelf() {
        return links.getSelf();
    }

    String getLinkFixtures() {
        return links.getFixtures();
    }

    String getLinkPlayers() {
        return links.getPlayers();
    }

    @Override
    public String toString() {
        return name;
    }
}
