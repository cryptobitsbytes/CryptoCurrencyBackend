package Twitter.DatabaseManager;

import DatabaseLayer.MongoDBInstance;
import Twitter.Models.Hashtag;
import Twitter.Models.Url;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

/**
 * Created by Lennard on 20-8-2017.
 */
public class TwitterDBManager {
    private static final MongoDBInstance MONGO_DB_INSTANCE = MongoDBInstance.getInstance();
    // TODO refactor to lessen dependency on implementation
    private static final MongoDatabase MONGO_DATABASE = MONGO_DB_INSTANCE.getDatabase("Twitter");
    private static final MongoCollection<Document> MONGO_COLLECTION = MONGO_DB_INSTANCE.getCollection("Twitter", MONGO_DATABASE);


    //TODO replace with generic POJO to document class
    private Document urlToDocument(Url url)
    {
        Document document = new Document();
        document.append("url", url.getDisplayUrl());
        document.append("expanded_url", url.getExpandedUrl());
        document.append("display_url", url.getDisplayUrl());
        document.append("indices", url.getIndices());
        return document;
    }

    private Url documentToUrl(Document document)
    {
        Url url = new Url();
        url.setUrl((String)document.get("url"));
        url.setExpandedUrl((String)document.get("expanded_url"));
        url.setDisplayUrl((String)document.get("display_url"));
        url.setIndices((List<Integer>)document.get("indices"));
        return url;
    }

    private Document hashtagToDocument(Hashtag hashtag)
    {
        Document document = new Document();
        document.append("text", hashtag.getText());
        document.append("indices", hashtag.getIndices());
        return document;
    }

    private Hashtag documentToHashtag(Document document)
    {
        Hashtag hashtag = new Hashtag();
        hashtag.setText((String)document.get("text"));
        hashtag.setIndices((List<Integer>)document.get("indices"));
        return hashtag;
    }
}
