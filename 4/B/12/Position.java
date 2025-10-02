// Позиции в бригаде
enum Position {
    CAPTAIN("Командир воздушного судна"),
    FIRST_OFFICER("Второй пилот"),
    NAVIGATOR("Штурман"),
    RADIO_OPERATOR("Радист"),
    FLIGHT_ATTENDANT("Стюардесса");

    private final String description;

    Position(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
