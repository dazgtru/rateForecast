package ru.liga;

import static ru.liga.CSVReader.readCSV;
import static ru.liga.Forecast.doForecast;

import java.util.ArrayList;

/**
 * Класс прогнозирования курса валют.
 *
 */
public class RateForecast {

    public static void main( String[] args ) {
        int amountOfDays = 7;
        ArrayList<String[]> newForecast;
        String[] currencies = {"EURO", "TRY", "USD"};

        for (String currency: currencies) {
            newForecast = readCSV(currency, amountOfDays);
            doForecast(newForecast, currency);
        }
    }

}