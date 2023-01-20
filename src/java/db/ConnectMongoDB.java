package db;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
public class ConnectMongoDB {
    
    public static MongoDatabase getConnect() throws Exception{
        String url = "mongodb://mongo:CtmYkTjratZFJYD6PZkb@containers-us-west-148.railway.app:7038";
        MongoClient mongoClient = MongoClients.create(url);
        MongoDatabase response = mongoClient.getDatabase("test");
        return response;
        
    }
}
