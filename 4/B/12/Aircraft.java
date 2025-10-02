class Aircraft {
    private String registrationNumber;
    private String model;
    private int capacity;
    private int maxRange; // в км
    private boolean operational;

    public Aircraft(String registrationNumber, String model, int capacity, int maxRange) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.capacity = capacity;
        this.maxRange = maxRange;
        this.operational = true;
    }

    // Getters
    public String getRegistrationNumber() { return registrationNumber; }
    public String getModel() { return model; }
    public int getCapacity() { return capacity; }
    public int getMaxRange() { return maxRange; }
    public boolean isOperational() { return operational; }

    // Setters
    public void setOperational(boolean operational) {
        this.operational = operational;
    }

    public boolean canFlyTo(Airport from, Airport to) {
        // Здесь должна быть логика расчета расстояния
        // Для простоты предположим, что все рейсы в пределах дальности
        return operational;
    }

    @Override
    public String toString() {
        return String.format("Aircraft{reg='%s', model='%s', capacity=%d, range=%dkm}",
                registrationNumber, model, capacity, maxRange);
    }
}
