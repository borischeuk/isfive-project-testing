package com.project.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class DatabaseHelper {
	
	public static final String DB_NAME = "vocab.db";
	private AndroidConnectionSource connectionSource;
	private static DatabaseHelper databaseHelper;
	
	public static DatabaseHelper getInstance(Context context) {
		
		if (context == null && databaseHelper == null)
			Log.v("Create database helper", "No DatabaseHelper instance is created");
		
		if(databaseHelper == null)
			databaseHelper = new DatabaseHelper(context);
		
		return databaseHelper;
		
	}
	
	public DatabaseHelper(Context context) {
		
		File file = new File(context.getCacheDir(), DB_NAME);
		
		if(!file.exists()) {
			
			try {

				loadFile(context, file);
				
			} catch (IOException e) {
				Log.v("IOException", e.toString());
			}
			
		}

		SQLiteDatabase db = SQLiteDatabase.openDatabase(file.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
		connectionSource = new AndroidConnectionSource(db);
	}
	
	public static void loadFile(Context context, File file) throws IOException {
		
		AssetManager assetManager = context.getAssets();

		InputStream is = assetManager.open(DB_NAME);
		FileOutputStream fos = new FileOutputStream(file);
		
		int size = is.available();
		byte[] buffer = new byte[size];
		int count = 0;
		while((count = is.read(buffer)) > 0) {

			fos.write(buffer, 0, count);

		}
		
		fos.close();
		is.close();
		
	}
	
    public <D extends Dao<T, ?>, T> D getDao(Class<T> clazz) throws Exception {
        if (connectionSource != null) {

        	try {
        		
        		String daoName = clazz.getName() + "DaoImpl";
        		Class<?> daoClass = (Class<?>) Class.forName(daoName);

        		Constructor<?> daoConstructor = daoClass.getConstructor(ConnectionSource.class);

        		Dao<?, ?> dao = (Dao<?, ?>) daoConstructor.newInstance(connectionSource);

        		D castDao = (D) dao;
        	
        		return castDao;
        		
        	} catch (ClassNotFoundException e) {
        		
        	}

        	return DaoManager.createDao(connectionSource, clazz);
        	
        }
        return null;
    }

}
