package ru.liga;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
    /**
     * Метод достаёт из csv файла последние курсы валюты.
     * @param currencyCode - код Валюты.
     * @param amountOfDays - количество дней которые требуется считать из файла.
     * @return - возвращает список массива строк.
     */
    public static ArrayList<String[]> readCSV(String currencyCode, int amountOfDays) {
        String filePath = "target/classes/" + currencyCode + ".csv";
        ArrayList<String[]> csvLines = new ArrayList<>();
        ArrayList<String[]> sortedForecasts = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader((filePath)));
            br.readLine();

            for (int i = 0; i < amountOfDays; i++) {
                csvLines.add(br.readLine().split(";"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = csvLines.size()-1; i >= (csvLines.size() - amountOfDays); i--) {
            sortedForecasts.add(csvLines.get(i));
        }
        return sortedForecasts;
    }
}
