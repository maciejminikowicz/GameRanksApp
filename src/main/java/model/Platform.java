package model;

public enum Platform {
    PLAYSTATION("Playstation"),
    PLAYSTATION_2("Playstation 2"),
    PLAYSTATION_3("Playstation 3"),
    PLAYSTATION_4("Playstation 4"),
    NINTENDO_64("Nintendo 64");

    private String displayName;

    Platform(String displayName) {
        this.displayName = displayName;
    }
}
