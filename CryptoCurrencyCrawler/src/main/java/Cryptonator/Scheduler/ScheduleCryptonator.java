package Cryptonator.Scheduler;

import Cryptonator.DatabaseManager.CryptonatorDBStorage;
import Cryptonator.Models.TickerResponse;
import Cryptonator.RequestClient.CryptonatorConnector;

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
//                CryptonatorConnector Api = new CryptonatorConnector();
//                try{
//                    TickerResponse tickerResponse = Api.connect();
//                    CryptonatorDBStorage cryptonatorDBStorage = new CryptonatorDBStorage();
//                    cryptonatorDBStorage.storeTickerResponse(tickerResponse);
//                    System.out.println("Succesfull storage");
//                }
//                catch (Exception e)
//                {
//                    System.out.println(e.getMessage());
//                }
            }
        };
        scheduledExecutorService.scheduleWithFixedDelay(runnable, 0, 60, TimeUnit.SECONDS);
    }

}
