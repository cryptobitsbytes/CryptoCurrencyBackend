package Cryptonator.DatabaseManager;

import Cryptonator.Models.TickerResponse;
import DatabaseLayer.Interface.IDatabase;
import DatabaseLayer.Implementation.MongoDatabaseImplementation;

import java.util.List;

/**
 * Created by Lennard on 10-8-2017.
 */
public class CryptonatorDBStorage {
    private IDatabase databaseImpl = null;

    public CryptonatorDBStorage() {
        this.databaseImpl = new MongoDatabaseImplementation("Ticker", "Ticker");
    }

    public void storeTickerResponse(TickerResponse tickerResponse)
    {
        databaseImpl.store(tickerResponse);
    }

    public List<TickerResponse> findAllTickerResponse()
    {
        return databaseImpl.findAll(TickerResponse.class);
    }
}
