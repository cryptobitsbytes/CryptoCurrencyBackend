package Twitter.DatabaseManager;

import DatabaseLayer.DocumentConverter;
import DatabaseLayer.MongoDBInstance;
import Twitter.Models.TwitterObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lennard on 20-8-2017.
 */
public class TwitterDBManager {
    private static final MongoDBInstance MONGO_DB_INSTANCE = MongoDBInstance.getInstance();
    // TODO refactor to lessen dependency on implementation
    private static final MongoDatabase MONGO_DATABASE = MONGO_DB_INSTANCE.getDatabase("Twitter");
    private static final MongoCollection<Document> MONGO_COLLECTION = MONGO_DB_INSTANCE.getCollection("Twitter", MONGO_DATABASE);

    public void storeTwitterObject(TwitterObject twitterObject)
    {
        Document document = twitterObjectToDocument(twitterObject);
        MONGO_DB_INSTANCE.insertOne(document, MONGO_COLLECTION);
    }
    public List<TwitterObject> findAllTickerResponse()
    {
        List<Document> documentList = MONGO_DB_INSTANCE.findAll(MONGO_COLLECTION);
        List<TwitterObject> twitterObjectList = new LinkedList<>();
        documentList.forEach((twitterObject) -> {
            twitterObjectList.add(documentToTwitterObject(twitterObject));
        });
        return twitterObjectList;
    }

    private Document twitterObjectToDocument(TwitterObject twitterObject)
    {
        return DocumentConverter.convertObjectToDocument(twitterObject);
    }

    private TwitterObject documentToTwitterObject(Document document)
    {
        return (TwitterObject)DocumentConverter.convertDocumentToObject(document, TwitterObject.class);
    }
}
