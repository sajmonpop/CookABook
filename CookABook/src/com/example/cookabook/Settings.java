package com.example.cookabook;

import android.app.Activity;
import android.os.Bundle;


public class Settings extends Activity{
	
	private Text2Speech t2s;

	Settings(Text2Speech text2speech){
		t2s = text2speech;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void test(){
		t2s.speakOut(0);
	}
	
	public void setChanges(){
		float f = 1;
		
		t2s.changePitch(f);
		t2s.changeSpeed(f);
	}
}
