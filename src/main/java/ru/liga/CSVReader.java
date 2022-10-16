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
        String filePath = MessageFormat.format("target/classes/{0}.csv", currencyCode);
        ArrayList<String[]> csvLines = new ArrayList<>();
        ArrayList<String[]> sortedForecasts = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader((filePath)))) {
            bufferedReader.readLine();

            for (int i = 0; i < amountOfDays; i++) {
                csvLines.add(bufferedReader.readLine().split(";"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int indexOfLastLine = csvLines.size()-1;
        int indexOfFirstLine = indexOfLastLine - amountOfDays;
        for (int i = indexOfLastLine; i > indexOfFirstLine; i--) {
            sortedForecasts.add(csvLines.get(i));
        }
        return sortedForecasts;
    }
}
