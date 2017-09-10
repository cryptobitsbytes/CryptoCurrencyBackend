package Cryptonator.Observable;

import Cryptonator.Models.TickerResponse;
import Cryptonator.RequestClient.CryptonatorConnector;

import java.util.Observable;

/**
 * Created by Lennard on 10-9-2017.
 */
public class CryptonatorObservable extends Observable {
    private CryptonatorConnector cryptonatorConnector = null;

    public CryptonatorObservable(CryptonatorConnector cryptonatorConnector) {
        this.cryptonatorConnector = cryptonatorConnector;
    }

    public void newTick(TickerResponse tickerResponse)
    {
        setChanged();
        notifyObservers(tickerResponse);
    }

    public void StartObserving()
    {
        try {
            cryptonatorConnector.connect();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
