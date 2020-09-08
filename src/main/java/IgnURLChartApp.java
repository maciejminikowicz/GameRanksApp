import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class IgnURLChartApp {

    public static final String IGN_URL = "https://www.ign.com/lists/top-100-games/100";



    public static void main(String[] args) throws IOException {

        ExecutorService executorService = Executors.newFixedThreadPool(12);

        executorService.submit(() -> {
            try {
                long start = System.currentTimeMillis();
                getChart(IGN_URL);
                long end = System.currentTimeMillis();
                long total = end - start;
                System.out.println("Total time: " + total + "ms");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }

    private static List<String> getChart(String url) throws IOException {
        URL metacriticLink = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(metacriticLink.openStream()));

        String inputLine;
        StringBuilder stringBuilder = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            stringBuilder.append(inputLine);
            stringBuilder.append(System.lineSeparator());
        }
        in.close();

        String content = stringBuilder.toString();
        String link = "";
        List<String> top100GamesList = new ArrayList<>();
        for (int i = 0; i < content.length(); i++) {
            i = content.indexOf("https://www.ign.com/games/", i);
            if (i < 0) {
                break;
            }
            String substring = content.substring(i);
            link = substring.split("\">")[0];
            top100GamesList.add(link);
        }
        for (String games : top100GamesList
        ) {
            System.out.println(games);
        }
        return top100GamesList;

    }






}
