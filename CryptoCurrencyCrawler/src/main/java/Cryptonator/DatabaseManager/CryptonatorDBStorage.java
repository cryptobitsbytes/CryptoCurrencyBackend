package Cryptonator.DatabaseManager;

import Cryptonator.Models.TickerResponse;
import DatabaseLayer.DocumentConverter;
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
        return DocumentConverter.convertObjectToDocument(tickerResponse);
    }

    private TickerResponse documentToTickerResponse(Document document)
    {
        return (TickerResponse) DocumentConverter.convertDocumentToObject(document, TickerResponse.class);
    }
}
