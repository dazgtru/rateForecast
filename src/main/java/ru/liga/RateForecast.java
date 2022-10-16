package ru.liga;

import static ru.liga.CSVReader.readCSV;
import static ru.liga.Forecast.doForecast;

import java.util.ArrayList;

/**
 * Класс прогнозирования курса валют.
 *
 */
public class RateForecast {

    public static void main(String[] args ) {
        int amountOfForecastDays = 7;
        String[] currencies = Currencies.getNames(Currencies.class);

        forecast(currencies, amountOfForecastDays);
    }

    private static void forecast(String[] currencies, int amountOfForecastDays) {
        ArrayList<String[]> newForecast;
        for (String currency: currencies) {
            newForecast = readCSV(currency, amountOfForecastDays);
            doForecast(newForecast, currency, amountOfForecastDays);
        }
    }

}