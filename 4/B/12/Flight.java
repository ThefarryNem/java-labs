import java.time.LocalDateTime;

class Flight {
	private String flightNumber;
	private Airport departureAirport;
	private Airport arrivalAirport;
	private Airport actualArrivalAirport; // Фактический аэропорт назначения
	private LocalDateTime scheduledDeparture;
	private LocalDateTime actualDeparture;
	private LocalDateTime actualArrival;
	private Aircraft aircraft;
	private Crew crew;
	private FlightStatus status;
	private String cancellationReason;
	private String diversionReason;

	public Flight(String flightNumber, Airport departureAirport, Airport arrivalAirport,
			LocalDateTime scheduledDeparture, Aircraft aircraft) {
		this.flightNumber = flightNumber;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.actualArrivalAirport = arrivalAirport;
		this.scheduledDeparture = scheduledDeparture;
		this.aircraft = aircraft;
		this.status = FlightStatus.SCHEDULED;
	}

	// Getters

	public String getFlightNumber() {
		return flightNumber;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public Airport getActualArrivalAirport() {
		return actualArrivalAirport;
	}

	public LocalDateTime getScheduledDeparture() {
		return scheduledDeparture;
	}

	public LocalDateTime getActualDeparture() {
		return actualDeparture;
	}

	public LocalDateTime getActualArrival() {
		return actualArrival;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public Crew getCrew() {
		return crew;
	}

	public FlightStatus getStatus() {
		return status;
	}

	public String getCancellationReason() {
		return cancellationReason;
	}

	public String getDiversionReason() {
		return diversionReason;
	}

	// Setters
	public void setCrew(Crew crew) {
		this.crew = crew;
	}

	public void checkFlightStatus() {
		if (!departureAirport.isOperational() || !arrivalAirport.isOperational()) {
			cancelFlight("Неблагоприятные погодные условия");
		} else if (!aircraft.isOperational()) {
			cancelFlight("Техническая неисправность самолета");
		} else if (crew == null || !crew.hasRequiredPositions()) {
			cancelFlight("Неполная бригада");
		} else {
			System.out.println("Рейс " + flightNumber + " готов к вылету");
		}
	}

	public void cancelFlight(String reason) {
		this.status = FlightStatus.CANCELLED;
		this.cancellationReason = reason;
		System.out.println("Рейс " + flightNumber + " ОТМЕНЕН. Причина: " + reason);
	}

	public void startFlight() {
		if (status == FlightStatus.SCHEDULED) {
			this.status = FlightStatus.IN_FLIGHT;
			this.actualDeparture = LocalDateTime.now();
			System.out.println("Рейс " + flightNumber + " вылетел по маршруту " +
					departureAirport.getCode() + " -> " + arrivalAirport.getCode());
		}
	}

	public void divertFlight(Airport alternateAirport, String reason) {
		if (status == FlightStatus.IN_FLIGHT) {
			this.actualArrivalAirport = alternateAirport;
			this.diversionReason = reason;
			this.status = FlightStatus.DIVERTED;
			System.out.println("Рейс " + flightNumber + " перенаправлен в " +
					alternateAirport.getName() + ". Причина: " + reason);
		}
	}

	public void completeFlight() {
		this.status = FlightStatus.COMPLETED;
		this.actualArrival = LocalDateTime.now();
		System.out.println("Рейс " + flightNumber + " завершен. Фактическое прибытие: " +
				actualArrivalAirport.getName());
	}

	@Override
	public String toString() {
		return String.format("Flight{number='%s', route=%s->%s, status=%s, aircraft=%s}",
				flightNumber, departureAirport.getCode(),
				arrivalAirport.getCode(), status.getDescription(),
				aircraft.getModel());
	}
}
