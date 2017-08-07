package DatabaseLayer;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by Lennard on 1-8-2017.
 */

public final class MongoDBInstance {
    private static String HOSTNAME = "localhost";
    private static int PORT = 27017;
    private static MongoClient mongoClient = new MongoClient(HOSTNAME, PORT);
    private static MongoDBInstance mongoDBInstance = null;

    public static MongoDBInstance getInstance() {
        if (mongoDBInstance == null) {
            mongoDBInstance = new MongoDBInstance();
        }
        return mongoDBInstance;
    }

    private MongoDBInstance() {
    }

    public MongoDatabase getDatabase(String databaseName) {
        return mongoClient.getDatabase(databaseName);
    }

    public MongoCollection<Document> getCollection(String collectionName, MongoDatabase mongoDatabase) {
        return mongoDatabase.getCollection(collectionName);
    }

    public void insertOne(Document document, MongoCollection<Document> mongoCollection) {
        mongoCollection.insertOne(document);
    }

    public void insertMany(List<Document> documentList, MongoCollection<Document> mongoCollection) {
        mongoCollection.insertMany(documentList);
    }

    public long count(MongoCollection<Document> mongoCollection)
    {
        return mongoCollection.count();
    }

    public FindIterable<Document> find(MongoCollection<Document> mongoCollection)
    {
        return mongoCollection.find();
    }

    public FindIterable<Document> find(MongoCollection<Document> mongoCollection, Bson filter)
    {
        return mongoCollection.find(filter);
    }

    public Document findFirst(MongoCollection<Document> mongoCollection)
    {
        return mongoCollection.find().first();
    }

    public Document findFirst(MongoCollection<Document> mongoCollection, Bson filter)
    {
        return mongoCollection.find(filter).first();
    }

    public List<Document> findAll(MongoCollection<Document> mongoCollection)
    {
        LinkedList<Document> documentLinkedList = new LinkedList<>();
        MongoCursor<Document> cursor = find(mongoCollection).iterator();
        try{
            while (cursor.hasNext())
            {
                documentLinkedList.add(cursor.next());
            }
        }
        finally
        {
            cursor.close();
        }
        return documentLinkedList;
    }

    public List<Document> findAll(MongoCollection<Document> mongoCollection, Bson filter)
    {
        LinkedList<Document> documentLinkedList = new LinkedList<>();
        MongoCursor<Document> cursor = find(mongoCollection, filter).iterator();
        try{
            while (cursor.hasNext())
            {
                documentLinkedList.add(cursor.next());
            }
        }
        finally
        {
            cursor.close();
        }
        return documentLinkedList;
    }

    public List<Document> queryAllEquals(MongoCollection<Document> mongoCollection, String key, Object value)
    {
        return findAll(mongoCollection, eq(key, value));
    }

    public Document queryFirstEquals(MongoCollection<Document> mongoCollection, String key, Object value)
    {
        return findFirst(mongoCollection, eq(key, value));
    }

    public UpdateResult updateOne(MongoCollection<Document> mongoCollection, Bson filter, Bson update)
    {
        return mongoCollection.updateOne(filter, update);
    }

    public UpdateResult updateMany(MongoCollection<Document> mongoCollection, Bson filter, Bson update)
    {
        return mongoCollection.updateMany(filter, update);
    }

    public DeleteResult deleteOne(MongoCollection<Document> mongoCollection, Bson filter)
    {
        return mongoCollection.deleteOne(filter);
    }

    public DeleteResult deleteMany(MongoCollection<Document> mongoCollection, Bson filter)
    {
        return mongoCollection.deleteMany(filter);
    }
}
