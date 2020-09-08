package model;

public class Game {
    private int rank;
    private String link;
    private String title;
    private String platform;
    private int releaseYear;

    public Game(int rank, String link, String title, String platform, int releaseYear) {
        this.rank = rank;
        this.link = link;
        this.title = title;
        this.platform = platform;
        this.releaseYear = releaseYear;
    }

    public String getLink() {
        return link;
    }

    public String getPlatform() {
        return platform;
    }

    public String getTitle() {
        return title;
    }

    public int getRank() {
        return rank;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    @Override
    public String toString() {
        return "model.Game{" +
                "rank=" + rank +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", platform='" + platform + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
