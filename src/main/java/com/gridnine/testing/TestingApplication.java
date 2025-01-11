package com.gridnine.testing;

import com.gridnine.testing.filter.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.filter.DepartureBeforeCurrentTimeFilter;
import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.GroundTimeExceedsTwoHoursFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TestingApplication {

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        FlightFilter departureBeforeCurrentTimeFilter = new DepartureBeforeCurrentTimeFilter();
        FlightFilter arrivalBeforeDepartureFilter = new ArrivalBeforeDepartureFilter();
        FlightFilter groundTimeExceedsTwoHoursFilter = new GroundTimeExceedsTwoHoursFilter();

        List<Flight> filteredFlights1 = departureBeforeCurrentTimeFilter.filter(flights);
        List<Flight> filteredFlights2 = arrivalBeforeDepartureFilter.filter(flights);
        List<Flight> filteredFlights3 = groundTimeExceedsTwoHoursFilter.filter(flights);

        System.out.println("Перелёты после исключения вылетов до текущего момента времени: ");
        for (Flight flight : filteredFlights1) {
            System.out.println(flight);
        }
        printDelimiter();

        System.out.println("Перелёты после исключения сегментов с датой прилёта раньше даты вылета: ");
        for (Flight flight : filteredFlights2) {
            System.out.println(flight);
        }
        printDelimiter();

        System.out.println("Перелёты после исключения перелётов с общим временем на земле более 2 часов: ");
        for (Flight flight : filteredFlights3) {
            System.out.println(flight);
        }
    }


    public static void printDelimiter() {
        System.out.println("------------------------------------");
    }
}