import java.util.*;
import java.time.LocalDateTime;

class Crew {
	private String id;
	private Flight flight;
	private List<CrewMember> crewMembers;
	private LocalDateTime formationDate;

	public Crew(String id, Flight flight) {
		this.id = id;
		this.flight = flight;
		this.crewMembers = new ArrayList<>();
		this.formationDate = LocalDateTime.now();
	}// Getters

	public String getId() {
		return id;
	}

	public Flight getFlight() {
		return flight;
	}

	public List<CrewMember> getCrewMembers() {
		return crewMembers;
	}

	public LocalDateTime getFormationDate() {
		return formationDate;
	}

	public void addCrewMember(CrewMember member) {
		if (!crewMembers.contains(member)) {
			crewMembers.add(member);
		}
	}

	public boolean hasRequiredPositions() {
		boolean hasCaptain = crewMembers.stream().anyMatch(m -> m.getPosition() == Position.CAPTAIN);
		boolean hasFirstOfficer = crewMembers.stream().anyMatch(m -> m.getPosition() == Position.FIRST_OFFICER);
		boolean hasNavigator = crewMembers.stream().anyMatch(m -> m.getPosition() == Position.NAVIGATOR);
		boolean hasRadioOperator = crewMembers.stream().anyMatch(m -> m.getPosition() == Position.RADIO_OPERATOR);
		boolean hasFlightAttendant = crewMembers.stream().anyMatch(m -> m.getPosition() == Position.FLIGHT_ATTENDANT);

		return hasCaptain && hasFirstOfficer && hasNavigator && hasRadioOperator && hasFlightAttendant;
	}

	@Override
	public String toString() {
		return String.format("Crew{id='%s', flight=%s, members=%d, complete=%s}",
				id, flight.getFlightNumber(), crewMembers.size(), hasRequiredPositions());
	}
}
