package com.example.cookabook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class RecipeReader {
	public String read(Context c, String filename){
		AssetManager am = c.getAssets();
		String recipe = "";
		try {
			InputStream is = am.open(filename);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String line = null;

			while((line=br.readLine())!=null) {
				Log.d("i while", line);
				recipe += line;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recipe;
	}

}
