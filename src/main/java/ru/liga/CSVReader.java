package ru.liga;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;

public class CSVReader {
    /**
     * Метод достаёт из csv файла последние курсы валюты.
     * @param currencyCode - код Валюты.
     * @param amountOfDays - количество дней которые требуется считать из файла.
     * @return - возвращает список массива строк.
     */
    public static ArrayList<String[]> readCSV(String currencyCode, int amountOfDays) {
        String filePath = "target/classes/" + currencyCode + ".csv"; //todo используй MessageFormat.format()
        ArrayList<String[]> csvLines = new ArrayList<>();
        ArrayList<String[]> sortedForecasts = new ArrayList<>();

        try {
            //todo
            // 1 - название лучше не сокращать, а писать полное название
            // 2 - BufferedReader не закрыл) т.е. br.close()
            // 3 - вытекает из предыдущего используй try-with-resources
            BufferedReader br = new BufferedReader(new FileReader((filePath)));
            br.readLine();

            for (int i = 0; i < amountOfDays; i++) {
                csvLines.add(br.readLine().split(";"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = csvLines.size()-1; i >= (csvLines.size()-1); i--) {  //todo  csvLines.size()-1 - в отдельную переменную, csvLines.size()-1 - в отдельную переменную -- так как вообще не понятно что тут происходит
            sortedForecasts.add(csvLines.get(i));
        }
        return sortedForecasts;
    }
}
