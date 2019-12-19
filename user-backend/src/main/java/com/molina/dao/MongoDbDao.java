package com.molina.dao;

import org.bson.Document;

import com.molina.util.Util;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDbDao {
	private static MongoClient mongoClient;
	private MongoCollection<Document> collection;
	
	public MongoCollection<Document> getCollection() {
		return this.collection;
	}
	
	protected void setCollection(String databaseName, String collectionName){
		MongoClientURI mongoClientURI = new MongoClientURI("mongodb://" + Util.DB_HOST + ":" + Util.DB_PORT);
		mongoClient = new MongoClient(mongoClientURI);
		MongoDatabase database = mongoClient.getDatabase(databaseName);
		collection = database.getCollection(collectionName);
	}
	
	public static void closeMongoConnection() {
		mongoClient.close();
	}
	
}
