import model.Game;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MetacriticFromFileChartApp {

    private static final String METACRITIC_PATH = "src/main/resources/metacritic.txt";


    public static void main(String[] args) throws IOException {

        List<Game> gamesFromFile = getGamesFromFile();
        for (Game games : gamesFromFile) {
            System.out.println(games);
        }

        System.out.println("-----------------------------------------------------------------");
        gamesGroupedByPlatform(gamesFromFile);
        System.out.println("-----------------------------------------------------------------");
        gamesSortedByYearFromOldest(gamesFromFile);
        System.out.println("-----------------------------------------------------------------");
        gamesSortedByYearFromNewest(gamesFromFile);



    }

    private static List<Game> getGamesFromFile() throws IOException {
        Scanner scanner = new Scanner(Path.of(METACRITIC_PATH));
        int rank = 0;
        String link = "";
        int releaseDate = 0;
        List<Game> listOfGames = new ArrayList<>();
        while (scanner.hasNext()) {
            rank = Integer.parseInt(scanner.nextLine());
            link = scanner.nextLine();
            String platform = link.substring(32).split("/")[0].replace("-", " ");
            String platformCorrect = firstLetterCapitalWithSingleSpace(platform);
            String titleUnformatted = link.substring(32).split("/")[1].replace("-", " ");
            String title = firstLetterCapitalWithSingleSpace(titleUnformatted);

            releaseDate = Integer.parseInt(scanner.nextLine());
            listOfGames.add(new Game(rank, link, title, platformCorrect, releaseDate));
        }
        return listOfGames;
    }

    public static String firstLetterCapitalWithSingleSpace(final String words) {
        return Stream.of(words.trim().split("\\s"))
                .filter(word -> word.length() > 0)
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));
    }

    private static void gamesSortedByYearFromOldest(List<Game> gamesFromFile) {
        List<Game> games = gamesFromFile.stream()
                .sorted(Comparator.comparing(Game::getReleaseYear))
                .collect(Collectors.toList());
        for (Game game : games) {
            System.out.println(game);
        }
    }

    private static void gamesSortedByYearFromNewest(List<Game> gamesFromFile) {
        List<Game> games = gamesFromFile.stream()
                .sorted(Comparator.comparing(Game::getReleaseYear).reversed())
                .collect(Collectors.toList());
        for (Game game : games) {
            System.out.println(game);
        }
    }

    private static void gamesGroupedByPlatform(List<Game> gamesFromFile) {
        Map<String, List<Game>> collect = gamesFromFile.stream()
                .collect(Collectors.groupingBy(Game::getPlatform));

        collect.forEach((platform, games) -> {
            System.out.println(platform);
            games.forEach(System.out::println);
        });
    }




}
