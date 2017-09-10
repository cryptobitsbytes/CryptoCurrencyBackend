package Cryptonator.Observable;

import Cryptonator.DatabaseManager.CryptonatorDBStorage;
import Cryptonator.Models.TickerResponse;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Lennard on 10-9-2017.
 */

//TODO make generic API obersver & observable
public class CryptonatorObserver implements Observer {
    private CryptonatorDBStorage cryptonatorDBStorage = null;

    public CryptonatorObserver(CryptonatorObservable cryptonatorObservable) {
        this.cryptonatorDBStorage = new CryptonatorDBStorage();
        cryptonatorObservable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o instanceof TickerResponse)
        {
            cryptonatorDBStorage.storeTickerResponse((TickerResponse) o);
        }
    }
}
