package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartureBeforeCurrentTimeFilterTest {
    @Test
    public void shouldExcludeFlightsWhereDepartureBeforeCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        Flight flight1 = new Flight(Arrays.asList(
                new Segment(now.plusHours(1), now.plusHours(2))));
        Flight flight2 = new Flight(Arrays.asList(
                new Segment(now.minusHours(1), now.plusHours(1))));

        List<Flight> flights = Arrays.asList(flight1, flight2);

        DepartureBeforeCurrentTimeFilter filter = new DepartureBeforeCurrentTimeFilter();
        List<Flight> filteredFlights = filter.filter(flights);

        assertEquals(1, filteredFlights.size());
        assertEquals(flight1, filteredFlights.get(0));
    }
}