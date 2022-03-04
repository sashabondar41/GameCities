package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cities {
    private static final String url = "https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D0%B3%D0%BE%D1%80%D0%BE%D0%B4%D0%BE%D0%B2_%D0%A0%D0%BE%D1%81%D1%81%D0%B8%D0%B8";
    private static String modifyCity(String city){
        StringBuilder result = new StringBuilder(city.toLowerCase(Locale.ROOT).replace('ё', 'е'));
        if (result.indexOf("(") != -1) {
            result.delete(city.indexOf("("), city.indexOf(")") + 1);
        }
        if (result.indexOf(" ") != -1) {
            result.deleteCharAt(city.indexOf(" "));
        }
        return new String(result);
    }

    public static List<String> getCities() throws IOException {
        List<String> cities = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Elements rows = doc.select("table").get(0).select("tr");
        for (int i = 2; i < rows.size(); i++) {
            String city = rows.get(i).select("td").get(2).select("a").get(0).attr("title");
            city = modifyCity(city);
            cities.add(city);
        }
        System.out.println(cities);
        return cities;
    }
}
