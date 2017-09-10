package DatabaseLayer.Implementation;

import DatabaseLayer.Connector.MongoDBInstance;
import DatabaseLayer.Interface.IDatabase;
import Reflection.DocumentConverter;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lennard on 10-9-2017.
 */
// TODO remove string based implementation of DB name and collection name
public class MongoDatabaseImplementation implements IDatabase {
    private static final MongoDBInstance MONGO_DB_INSTANCE = MongoDBInstance.getInstance();
    private com.mongodb.client.MongoDatabase MONGO_DATABASE = null;
    private MongoCollection<Document> MONGO_COLLECTION = null;

    public MongoDatabaseImplementation(String databaseName, String collectionName) {
       this.MONGO_DATABASE = MONGO_DB_INSTANCE.getDatabase(databaseName);
       this.MONGO_COLLECTION = MONGO_DB_INSTANCE.getCollection(collectionName, this.MONGO_DATABASE);
    }

    public  void store(Object o)
    {
        Document document = convertToDocument(o);
    }

    public <T> List<T> findAll(Class<T> tClass)
    {
        List<Document> documentList = MONGO_DB_INSTANCE.findAll(MONGO_COLLECTION);
        List<T> returnList = new LinkedList<>();
        documentList.forEach((document) -> {
            returnList.add(convertToObject(document, tClass));
        });
        return returnList;
    }

    private Document convertToDocument(Object o)
    {
        return DocumentConverter.convertObjectToDocument(o);
    }

    private <T> T convertToObject(Document document, Class<T> tClass)
    {
        return (T)  DocumentConverter.convertDocumentToObject(document,tClass);
    }
}
