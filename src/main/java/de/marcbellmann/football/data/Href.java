package de.marcbellmann.football.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Marc Bellmann &lt;marc.bellmann@planyourtrip.travel&gt;
 */
class Href {

    @JsonProperty("href")
    private String href;

    String getHref() {
        return href;
    }

    @Override
    public String toString() {
        return href;
    }
}
