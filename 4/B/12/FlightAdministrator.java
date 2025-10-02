import java.util.*;
import java.time.LocalDateTime;

class FlightAdministrator {
	private int id;
	private String name;
	private List<Flight> managedFlights;
	private static int crewCounter = 1;

	public FlightAdministrator(int id, String name) {
		this.id = id;
		this.name = name;
		this.managedFlights = new ArrayList<>();
	}

	// Getters
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Flight> getManagedFlights() {
		return managedFlights;
	}

	public Flight createFlight(String flightNumber, Airport departure, Airport arrival,
			LocalDateTime departureTime, Aircraft aircraft) {
		Flight flight = new Flight(flightNumber, departure, arrival, departureTime, aircraft);
		managedFlights.add(flight);
		System.out.println("Администратор " + name + " создал рейс: " + flightNumber);
		return flight;
	}

	public Crew createCrew(Flight flight, CrewMember captain, CrewMember firstOfficer,
			CrewMember navigator, CrewMember radioOperator) {
		Crew crew = new Crew("CREW-" + crewCounter++, flight);
		crew.addCrewMember(captain);
		crew.addCrewMember(firstOfficer);
		crew.addCrewMember(navigator);
		crew.addCrewMember(radioOperator);

		flight.setCrew(crew);
		System.out.println("Сформирована бригада для рейса " + flight.getFlightNumber());
		return crew;
	}

	public void addFlightAttendant(Crew crew, CrewMember flightAttendant) {
		crew.addCrewMember(flightAttendant);
		System.out.println("Добавлен бортпроводник: " + flightAttendant.getName());
	}

	public void cancelFlight(Flight flight, String reason) {
		flight.cancelFlight(reason);
	}

	@Override
	public String toString() {
		return String.format("Administrator{name='%s', managedFlights=%d}",
				name, managedFlights.size());
	}
}
