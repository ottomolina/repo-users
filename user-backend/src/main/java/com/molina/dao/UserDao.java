package com.molina.dao;

import java.util.List;

import org.bson.Document;

import com.molina.util.Util;

public class UserDao extends MongoDbDao {
	
	public UserDao() {
		setCollection(Util.DB_NAME, Util.COLLECTION_USER);
	}
	
	public void insertUser(Document document) {
		getCollection().insertOne(document);
	}
	
	public void insertUser(List<Document> document) {
		getCollection().insertMany(document);
	}
	
	public void getUsers(Document filter) {
		getCollection().find(filter);
	}
	
	public long updateUser(Document filter, Document update) {
		return getCollection().updateOne(filter, update).getModifiedCount();
	}
	
	public long deleteUser(Document filter) {
		return getCollection().deleteOne(filter).getDeletedCount();
	}
	
}
