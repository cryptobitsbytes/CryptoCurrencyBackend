package Cryptonator.Scheduler;

import Cryptonator.Models.TickerResponse;
import Cryptonator.RequestClient.CryptonatorApi;
import Cryptonator.TickerOutput.TickerCSVFile;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lennard on 29-7-2017.
 */
public class ScheduleCryptonator {
    private final static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);


    //TODO make a generalized scheduler
    public static void callCryptonatorAndStoreData()
    {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                CryptonatorApi Api = new CryptonatorApi();
                try{
                    TickerResponse tickerResponse = Api.callApi();
                    TickerCSVFile csvFile = new TickerCSVFile();
                    csvFile.writeTickerResponseToCSV(tickerResponse);
                    System.out.println("Succesfull storage");
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        };
        scheduledExecutorService.scheduleWithFixedDelay(runnable, 0, 60, TimeUnit.SECONDS);
    }

}
