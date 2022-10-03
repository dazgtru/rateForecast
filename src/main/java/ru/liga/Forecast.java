package ru.liga;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class Forecast {
    final private static LocalDate TODAY = LocalDate.now();
    final private static DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Метод производит прогноз курса валюты на основе принятых данных.
     * @param oldRates - список массивов строк с последними датами, курсами валюты и наименованием валюты.
     * @param currencyCode - код валюты.
     */
    public static void doForecast(ArrayList<String[]> oldRates, String currencyCode) {
        LocalDate dateOfForecast = LocalDate.parse(oldRates.get(oldRates.size()-1)[1],
                DATE_TIME_FORMATTER);
        ArrayList<Double> onlyRate = new ArrayList<>();
        Double rateOfForecast;

        for (String[] oldRate : oldRates) {
            onlyRate.add(Double.valueOf(oldRate[2].replace(",", ".")));
        }

        while (!dateOfForecast.isEqual(TODAY.plusDays(7))) {
            rateOfForecast = 0.0;
            for (int i = onlyRate.size()-7; i < onlyRate.size(); i++) {
                rateOfForecast += onlyRate.get(i);
            }
            rateOfForecast /= 7;
            onlyRate.add(rateOfForecast);
            dateOfForecast = dateOfForecast.plusDays(1);

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

        if (dateOfForecast.isEqual(TODAY.plusDays(1))) {
            System.out.println("rate " + currencyCode + " tomorrow "+ dayOfWeek + " "
                    + formattedDate + " - " + formattedRate);
            System.out.println("rate " + currencyCode + " week");
        }
        System.out.println("\t" + dayOfWeek  + " " + formattedDate + " - " + formattedRate);
    }
}
