package de.marcbellmann.football.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marc Bellmann &lt;marc.bellmann@planyourtrip.travel&gt;
 */
public class Fixtures {

    private List<Fixture> fixtures;

    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public List<Fixture> getLast5Fixtures() {
        final List<Fixture> fixtures = new ArrayList<>(this.fixtures)
                .stream()
                .sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate()))
                .filter(Fixture::isResultSet)
                .collect(Collectors.toList());

        return fixtures.subList(0, Integer.min(fixtures.size(), 5));
    }
}
