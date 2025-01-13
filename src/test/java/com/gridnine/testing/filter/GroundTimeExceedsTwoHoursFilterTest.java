package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroundTimeExceedsTwoHoursFilterTest {

    @Test
    public void shouldExcludeFlightsWhereGroundTimeExceedsTwoHours() {
        LocalDateTime now = LocalDateTime.now();

        Flight validFlight = new Flight(Arrays.asList(
                new Segment(now.plusHours(1), now.plusHours(2)),
                new Segment(now.plusHours(3), now.plusHours(4))
        ));

        Flight invalidFlight = new Flight(Arrays.asList(
                new Segment(now.plusHours(1), now.plusHours(2)),
                new Segment(now.plusHours(5), now.plusHours(6))
        ));

        List<Flight> flights = Arrays.asList(validFlight, invalidFlight);

        GroundTimeExceedsTwoHoursFilter filter = new GroundTimeExceedsTwoHoursFilter();
        List<Flight> filteredFlights = filter.filter(flights);

        assertEquals(1, filteredFlights.size());
        assertEquals(validFlight, filteredFlights.get(0));
    }
}