package com.example.cookabook;

<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> Layout
import java.util.Locale;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.util.Log;

public class Text2Speech implements TextToSpeech.OnInitListener {
	/** Called when the activity is first created. */

	private TextToSpeech tts;
<<<<<<< HEAD
	private Button btnStart;
	private String[] txtText;
	private int line=0;


	public Text2Speech(Button btn, String aText, Context cont) {



		tts = new TextToSpeech(cont, this);
		btnStart = btn;
		Log.d("hello","t2s "+ aText);
		txtText = aText.split("\\.");
		Log.d("hello","t2s "+ txtText.length);

		// button on click event
		btnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				speakOut(0);
=======
	private Button btnSpeak;
	private EditText txtText;


	public Text2Speech(Button btn, EditText eText, Context cont) {

		

		tts = new TextToSpeech(cont, this);

		btnSpeak = btn;

		txtText = eText;

		// button on click event
		btnSpeak.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				speakOut();
>>>>>>> Layout
			}

		});
	}

<<<<<<< HEAD

=======
	
>>>>>>> Layout
	public void onDestroy() {
		// Don't forget to shutdown tts!
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
	}

	@Override
	public void onInit(int status) {

		if (status == TextToSpeech.SUCCESS) {

			int result = tts.setLanguage(Locale.US);

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "This Language is not supported");
			} else {
<<<<<<< HEAD
				btnStart.setEnabled(true);
				//speakOut(0);
=======
				btnSpeak.setEnabled(true);
				speakOut();
>>>>>>> Layout
			}

		} else {
			Log.e("TTS", "Initilization Failed!");
		}

	}

<<<<<<< HEAD
	public void changePitch(float newPitch){
		tts.setPitch(newPitch);
	}
	public void changeSpeed(float newSpeed){
		tts.setSpeechRate(newSpeed);
	}


	public void speakOut(int nextline) {
		line+=nextline;
		if(line>=0&&line<txtText.length){

			String text = txtText[line].toString();
			Log.d("hello",text);
			tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
		}else{
			tts.speak("Error!", TextToSpeech.QUEUE_FLUSH, null);
			line=0;
		}
=======
	private void speakOut() {
		System.out.print("inne i speakout. ");
		String text = txtText.getText().toString();

		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
>>>>>>> Layout
	}
}