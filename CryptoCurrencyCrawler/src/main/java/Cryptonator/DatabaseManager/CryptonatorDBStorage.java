package Cryptonator.DatabaseManager;

import Cryptonator.Models.Ticker;
import Cryptonator.Models.TickerResponse;
import DatabaseLayer.MongoDBInstance;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lennard on 10-8-2017.
 */
public class CryptonatorDBStorage {
    private static final MongoDBInstance MONGO_DB_INSTANCE = MongoDBInstance.getInstance();
    // TODO refactor to lessen dependency on implementation
    private static final MongoDatabase MONGO_DATABASE = MONGO_DB_INSTANCE.getDatabase("Ticker");
    private static final MongoCollection<Document> MONGO_COLLECTION = MONGO_DB_INSTANCE.getCollection("Ticker", MONGO_DATABASE);

    public void storeTickerResponse(TickerResponse tickerResponse)
    {
        Document document = tickerResponseToDocument(tickerResponse);
        MONGO_DB_INSTANCE.insertOne(document, MONGO_COLLECTION);
    }

    public List<TickerResponse> findAllTickerResponse()
    {
        List<Document> documentList = MONGO_DB_INSTANCE.findAll(MONGO_COLLECTION);
        List<TickerResponse> tickerResponseList = new LinkedList<>();
        documentList.forEach((ticker) -> {
            tickerResponseList.add(documentToTickerResponse(ticker));
        });
        return tickerResponseList;
    }

    // TODO create generic document to POJO generator
    private Document tickerResponseToDocument(TickerResponse tickerResponse)
    {
        Document ticker = tickerToDocument(tickerResponse.getTicker());
        Document document = new Document();
        document.append("timestamp",tickerResponse.getTimestamp());
        document.append("ticker", ticker);
        return document;
    }
    private Document tickerToDocument(Ticker ticker)
    {
        Document document = new Document();
        document.append("base", ticker.getBase());
        document.append("target", ticker.getTarget());
        document.append("price", ticker.getPrice());
        document.append("volume", ticker.getVolume());
        document.append("change", ticker.getChange());
        return  document;
    }

    private TickerResponse documentToTickerResponse(Document document)
    {
        TickerResponse tickerResponse = new TickerResponse();
        tickerResponse.setTimestamp((int)document.get("timestamp"));
        tickerResponse.setTicker(documentToTicker((Document)document.get("ticker")));
        return tickerResponse;
    }

    private Ticker documentToTicker(Document document)
    {
        Ticker ticker = new Ticker();
        ticker.setBase((String)document.get("base"));
        ticker.setTarget((String)document.get("target"));
        ticker.setPrice((String)document.get("price"));
        ticker.setVolume((String)document.get("volume"));
        ticker.setChange((String)document.get("change"));
        return ticker;
    }
}
