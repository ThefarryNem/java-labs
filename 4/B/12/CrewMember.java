class CrewMember {
    private int id;
    private String name;
    private Position position;
    private int experience; // в годах

    public CrewMember(int id, String name, Position position, int experience) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.experience = experience;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public Position getPosition() { return position; }
    public int getExperience() { return experience; }

    public void reportTechnicalIssue(Flight flight, String issue, Airport alternateAirport) {
        System.out.println(position.getDescription() + " " + name +
                " сообщает о неисправности: " + issue);
        flight.divertFlight(alternateAirport, issue);
    }

    @Override
    public String toString() {
        return String.format("CrewMember{name='%s', position=%s, experience=%d years}",
                name, position.getDescription(), experience);
    }
}
