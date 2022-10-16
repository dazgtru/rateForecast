package ru.liga;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class Forecast {
    final private static LocalDate TODAY = LocalDate.now();
    final private static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Метод производит прогноз курса валюты на основе принятых данных.
     * @param csvList - список массивов строк с последними датами, курсами валюты и наименованием валюты.
     * @param currencyCode - код валюты.
     */
    public static void doForecast(ArrayList<String[]> csvList, String currencyCode, int amountOfForecastDays) {
        int indexOfFirstLine;
        int indexOfLastLine;
        int indexOfDatesColumn = 1;
        int indexOfRatesColumn = 2;
        int nextDay = 1;

        indexOfLastLine = csvList.size()-1;
        LocalDate dateOfForecast = LocalDate.parse(csvList.get(indexOfLastLine)[indexOfDatesColumn], DATE_TIME_FORMATTER);
        ArrayList<Double> onlyRates = new ArrayList<>();
        Double rateOfForecast;

        for (String[] oldRate : csvList) {
            onlyRates.add(Double.valueOf(oldRate[indexOfRatesColumn].replace(",", ".")));
        }

        while (!dateOfForecast.isEqual(TODAY.plusDays(amountOfForecastDays))) {
            rateOfForecast = 0.0;
            indexOfFirstLine = onlyRates.size() - amountOfForecastDays;
            indexOfLastLine = onlyRates.size()-1;

            for (int i = indexOfFirstLine; i <= indexOfLastLine; i++) {
                rateOfForecast += onlyRates.get(i);
            }
            rateOfForecast /= amountOfForecastDays;
            onlyRates.add(rateOfForecast);
            dateOfForecast = dateOfForecast.plusDays(nextDay);

            if (dateOfForecast.isAfter(TODAY))
                printForecast(dateOfForecast, currencyCode, rateOfForecast);
        }
        System.out.println("-----------");
    }

    /**
     * Метод выводит в консоль результаты вычислений.
     * @param dateOfForecast - дата прогноза.
     * @param currencyCode - код валюты.
     * @param rateOfForecast - прогнозируемый курс валюты.
     */
    private static void printForecast(LocalDate dateOfForecast, String currencyCode, Double rateOfForecast) {
        Locale localeRu = new Locale("ru", "RU");
        String dayOfWeek = dateOfForecast.getDayOfWeek().getDisplayName(TextStyle.NARROW, localeRu);
        String formattedRate = String.format("%.2f", rateOfForecast);
        String formattedDate = dateOfForecast.format(DATE_TIME_FORMATTER);
        int nextDay = 1;

        if (dateOfForecast.isEqual(TODAY.plusDays(nextDay))) {
            System.out.println("rate " + currencyCode + " tomorrow "+ dayOfWeek + " "
                    + formattedDate + " - " + formattedRate);
            System.out.println(MessageFormat.format("rate {0} week", currencyCode));
        }
        System.out.println(MessageFormat.format("\t {0} {1} {2}", dayOfWeek, formattedDate, formattedRate));
    }
}
