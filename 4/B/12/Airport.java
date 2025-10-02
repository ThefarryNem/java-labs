class Airport {
    private String code;
    private String name;
    private String city;
    private WeatherConditions weatherConditions;

    public Airport(String code, String name, String city) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.weatherConditions = WeatherConditions.GOOD;
    }

    // Getters
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public WeatherConditions getWeatherConditions() { return weatherConditions; }

    // Setters
    public void setWeatherConditions(WeatherConditions weatherConditions) {
        this.weatherConditions = weatherConditions;
        System.out.println("Погода в аэропорту " + name + " изменена на: " + weatherConditions.getDescription());
    }

    public boolean isOperational() {
        return weatherConditions == WeatherConditions.GOOD ||
                weatherConditions == WeatherConditions.RAIN;
    }

    @Override
    public String toString() {
        return String.format("Airport{code='%s', name='%s', city='%s', weather=%s}",
                code, name, city, weatherConditions.getDescription());
    }
}
