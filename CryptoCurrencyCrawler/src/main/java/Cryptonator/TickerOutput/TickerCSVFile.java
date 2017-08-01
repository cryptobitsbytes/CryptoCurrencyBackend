package Cryptonator.TickerOutput;

import Cryptonator.CurrencyEnum;
import Cryptonator.Models.Ticker;
import Cryptonator.Models.TickerResponse;
import Cryptonator.OutputData.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lennard on 25-7-2017.
 */
public class TickerCSVFile {

    private static final String BASE_PATH = "C:\\Users\\Lennard\\Desktop\\CryptoCurrency\\Tickers\\";
    private static final String FILE_NAME_SEPERATOR = "_";
    private static final String FILE_NAME_EXTENSION = ".csv";

    public void writeTickerResponseToCSV(TickerResponse tickerResponse) throws IOException
    {
        Ticker ticker = tickerResponse.getTicker();
        File file = createTickerResponseCSVFile(ticker);
        boolean needHeader = !fileExistsAndNotDirectory(file);
        // true as second parameter to append instead of overwrite to file
        FileWriter fileWriter = new FileWriter(file, true);
        if(needHeader)
        {
            // New file, add headers
            CSVWriter.writeLine(fileWriter, listTickerCSVHeaders());
        }
        CSVWriter.writeLine(fileWriter, tickerResponseToCSVFormat(tickerResponse));
        fileWriter.close();
    }

    private File createTickerResponseCSVFile(Ticker ticker) throws IOException
    {
        String filePath = filePathFromTicker(ticker);
        File csvFile = new File(filePath);
        return csvFile;
    }

    private List<String> listTickerCSVHeaders()
    {
        ArrayList<String> csvHeaders = new ArrayList<String>()
        {{
            add("Base");
            add("Target");
            add("Price");
            add("Volume");
            add("Change");
            add("Timestamp");
        }};
        return  csvHeaders;
    }

    private List<String> tickerResponseToCSVFormat(TickerResponse tickerResponse)
    {
        Ticker ticker = tickerResponse.getTicker();
        ArrayList<String> tickerValues = new ArrayList<String>()
        {{
            add(ticker.getBase());
            add(ticker.getTarget());
            add(ticker.getPrice());
            add(ticker.getVolume());
            add(ticker.getChange());
            add(Integer.toString(tickerResponse.getTimestamp()));
        }};
        return  tickerValues;
    }

    private boolean fileExistsAndNotDirectory(File file)
    {
        return file.exists() && !file.isDirectory();
    }

    private String filePathFromTicker(Ticker ticker)
    {
        LocalDate date = LocalDate.now();
        int day = date.getDayOfYear();
        int year = date.getYear();
        String rate = CurrencyEnum.valueOf(ticker.getBase().toUpperCase()).generateRate(CurrencyEnum.valueOf(ticker.getTarget().toUpperCase()));
        return buildCSVStringFromRate(rate, day, year);
    }

    private String buildCSVStringFromRate(String rate, int day, int year)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_PATH);
        stringBuilder.append(rate);
        stringBuilder.append(FILE_NAME_SEPERATOR);
        stringBuilder.append(day);
        stringBuilder.append(FILE_NAME_SEPERATOR);
        stringBuilder.append(year);
        stringBuilder.append(FILE_NAME_EXTENSION);
        return  stringBuilder.toString();
    }

}
