package com.example.cookabook;

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
=======
>>>>>>> Layout
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
<<<<<<< HEAD
import android.content.res.AssetManager;
=======
>>>>>>> Layout
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
<<<<<<< HEAD
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
=======
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
>>>>>>> Layout
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
<<<<<<< HEAD
=======
import android.app.ListActivity;
>>>>>>> Layout
import android.app.SearchManager;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.devspark.sidenavigation.ISideNavigationCallback;
import com.devspark.sidenavigation.SideNavigationView;
import com.devspark.sidenavigation.SideNavigationView.Mode;

public class MainActivity extends SherlockActivity implements ISideNavigationCallback {

	public static final String EXTRA_TITLE = "com.devspark.sidenavigation.sample.extra.MTGOBJECT";
	public static final String EXTRA_RESOURCE_ID = "com.devspark.sidenavigation.sample.extra.RESOURCE_ID";
	public static final String EXTRA_MODE = "com.devspark.sidenavigation.sample.extra.MODE";
	private SideNavigationView sideNavigationView;
	private EditText eText;
	private Spinner msTextMatches;
	private Button speakBtn;
	private ListView mlvTextMatches;
	private TextView tv;
	private static final int VOICE_RECOGNITION_REQUEST_CODE = 1001;
	private SpeechRecognizer sr;
	private MainActivity ma;
<<<<<<< HEAD
	private Text2Speech t2s;
	private RecipeReader rr;
=======
>>>>>>> Layout
	private boolean killCommanded = true;
	Intent intent;
	private Handler mHandler = new Handler();

	//legel commands
	private static final String[] VALID_COMMANDS = {
		"next",
		"previous",
		"repeat",
		"close"
	};
	//private MyList weekList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sideNavigationView = (SideNavigationView) findViewById(R.id.side_navigation_view);
		sideNavigationView.setMenuItems(R.menu.side_navigation_menu);
		sideNavigationView.setMenuClickCallback(this);

		if (getIntent().hasExtra(EXTRA_TITLE)) {
			String title = getIntent().getStringExtra(EXTRA_TITLE);
			int resId = getIntent().getIntExtra(EXTRA_RESOURCE_ID, 0);
			setTitle(title);

			sideNavigationView.setMode(getIntent().getIntExtra(EXTRA_MODE, 0) == 0 ? Mode.LEFT : Mode.RIGHT);
		}


		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
<<<<<<< HEAD
		
		rr= new RecipeReader();
		String recipe = rr.read(this, "recipe_guacamole.txt");
=======
>>>>>>> Layout

		//weekList  = new MyList();
		Button btn = (Button) findViewById(R.id.searchButton);
		speakBtn = (Button) findViewById(R.id.speakButton);
		EditText eText = (EditText) findViewById(R.id.searchBox);
		msTextMatches = (Spinner) findViewById(R.id.sNoOfMatches);
<<<<<<< HEAD
		t2s = new Text2Speech(btn, recipe, this);
		
=======
		Text2Speech tts = new Text2Speech(btn, eText, this);
>>>>>>> Layout
		mlvTextMatches = (ListView) findViewById(R.id.weeklyList);
		tv = (TextView) findViewById(R.id.textView);

		ArrayList<String> textMatchList = new ArrayList<String>();
		textMatchList.add("hoj");
		tv.setText("yo simon");

		mlvTextMatches.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
				textMatchList));
<<<<<<< HEAD
		sr = SpeechRecognizer.createSpeechRecognizer(this);
=======
		sr = SpeechRecognizer.createSpeechRecognizer(this);       
>>>>>>> Layout
		sr.setRecognitionListener(new listener());
		checkVoiceRecognition();

		/*ListView listView = (ListView) findViewById(R.id.weeklyList);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
          "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
          "Linux", "OS/2" };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
          android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter); */

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);
		if (sideNavigationView.getMode() == Mode.RIGHT) {
			menu.findItem(R.id.mode_right).setChecked(true);
		} else {
			menu.findItem(R.id.mode_left).setChecked(true);
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			sideNavigationView.toggleMenu();
			break;
		case R.id.mode_left:
			item.setChecked(true);
			sideNavigationView.setMode(Mode.LEFT);
			break;
		case R.id.mode_right:
			item.setChecked(true);
			sideNavigationView.setMode(Mode.RIGHT);
			break;

		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	@Override
	public void onSideNavigationItemClick(int itemId) {
		Log.d("MyApp","I am here");
		// 1. Instantiate an AlertDialog.Builder with its constructor
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		// 2. Chain together various setter methods to set the dialog characteristics
		builder.setMessage("Change settings!")
		.setTitle("Settings");

		// 3. Get the AlertDialog from create()
		AlertDialog dialog = builder.create();

		dialog.show();

		// TODO Auto-generated method stub

	} 

	@Override
	public void onBackPressed() {
		// hide menu if it shown
		if (sideNavigationView.isShown()) {
			sideNavigationView.hideMenu();
		} else {
			super.onBackPressed();
		}
	}

	/**
	 * Start activity from SideNavigation.
	 * 
	 * @param title title of Activity
	 * @param resId resource if of background image
	 */
	private void invokeActivity(String title, int resId) {
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra(EXTRA_TITLE, title);
		intent.putExtra(EXTRA_RESOURCE_ID, resId);
		intent.putExtra(EXTRA_MODE, sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);

		// all of the other activities on top of it will be closed and this
		// Intent will be delivered to the (now on top) old activity as a
		// new Intent.
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		startActivity(intent);
		// no animation of transition
		overridePendingTransition(0, 0);

	}


	public void speak(View view) {
		killCommanded=false;
		Log.d("MyApp","pre speak ()");


		intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);        
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,"voice.recognition.test");

		intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,5); 
		sr.startListening(intent);

	}
	public void checkVoiceRecognition() {
		// Check if voice recognition is present
		PackageManager pm = getPackageManager();
		List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(
				RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
		if (activities.size() == 0) {
			speakBtn.setEnabled(false);
			speakBtn.setText("Voice recognizer not present");
			Toast.makeText(this, "Voice recognizer not present",
					Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == VOICE_RECOGNITION_REQUEST_CODE)

			//If Voice recognition is successful then it returns RESULT_OK
			if(resultCode == RESULT_OK) {

				ArrayList<String> textMatchList = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

				if (!textMatchList.isEmpty()) {
					// If first Match contains the 'search' word
					// Then start web search.
					if (textMatchList.get(0).contains("search")) {

						String searchQuery = textMatchList.get(0);
						searchQuery = searchQuery.replace("search","");
						Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
						search.putExtra(SearchManager.QUERY, searchQuery);
						startActivity(search);
					} else {
						// populate the Matches
						mlvTextMatches
						.setAdapter(new ArrayAdapter<String>(this,
								android.R.layout.simple_list_item_1,
								textMatchList));
					}

				}
				//Result code for various error.
			}else if(resultCode == RecognizerIntent.RESULT_AUDIO_ERROR){
				showToastMessage("Audio Error");
			}else if(resultCode == RecognizerIntent.RESULT_CLIENT_ERROR){
				showToastMessage("Client Error");
			}else if(resultCode == RecognizerIntent.RESULT_NETWORK_ERROR){
				showToastMessage("Network Error");
			}else if(resultCode == RecognizerIntent.RESULT_NO_MATCH){
				showToastMessage("No Match");
			}else if(resultCode == RecognizerIntent.RESULT_SERVER_ERROR){
				showToastMessage("Server Error");
			}
		String s = mlvTextMatches.getItemAtPosition(0).toString();
		if( !mlvTextMatches.getItemAtPosition(0).toString().equals("föregående")){
			Log.d("checken", "|"+mlvTextMatches.getItemAtPosition(0).toString()+"|"+"föregående"+"|");
			speak(eText);
		}else{
			super.onActivityResult(requestCode, resultCode, data);}
	}
	/**
	 * Helper method to show the toast message
	 **/
	void showToastMessage(String message){
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
	class listener implements RecognitionListener          
	{
		public static final String EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS="20";
		public void onReadyForSpeech(Bundle params)
		{
			Log.d("lyssnaren", "onReadyForSpeech");
		}
		public void onBeginningOfSpeech()
		{
			Log.d("lyssnaren", "onBeginningOfSpeech");
		}
		public void onRmsChanged(float rmsdB)
		{
			Log.d("lyssnaren", "onRmsChanged");
		}
		public void onBufferReceived(byte[] buffer)
		{
			Log.d("lyssnaren", "onBufferReceived");
		}
		public void onEndOfSpeech()
		{
			Log.d("lyssnaren", "onEndofSpeech");
			if(!killCommanded)
				sr.startListening(intent);
		}
		public void onError(int error)
		{
			Log.d("lyssnaren",  "error " +  error);
			//mText.setText("error " + error);
		}
		public void onResults(Bundle results)
		{
			if(!killCommanded){
				ArrayList<String> matches = null;
				if(results != null){
					matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
					if(matches != null){
						Log.d("HHH", "results are " + matches.toString());
<<<<<<< HEAD
						Log.d("HHH", "and size is " + matches.size());
						
=======
>>>>>>> Layout
						final ArrayList<String> matchesStrings = matches;
						processCommand(matchesStrings);
						Log.d("Hej hurru! on result", ""+killCommanded);
					}
					sr.startListening(intent);
					Log.d("i if", ""+killCommanded);}
				else{
					sr.stopListening();
					Log.d("i else", ""+killCommanded);}

<<<<<<< HEAD
			}   
=======
			}


			//mlvTextMatches.setAdapter(new ArrayAdapter<String>(MainActivity,android.R.layout.simple_list_item_1,data));
			//mText.setText("results: "+String.valueOf(data.size()));        
>>>>>>> Layout
		}
		public void onPartialResults(Bundle partialResults)
		{
			Log.d("lyssnaren", "onPartialResults");
		}
		public void onEvent(int eventType, Bundle params)
		{
			Log.d("lyssnaren", "onEvent " + eventType);
		}
	}
	public void setTextInList(ArrayList<String> arr){
		mlvTextMatches.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr));

	}
	private String getResponse(int command){
		//Calendar c = Calendar.getInstance();
		Log.d("checkern innan ", ""+killCommanded);
		String retString =  "I'm sorry, Simon. I'm afraid I can't do that.";
		SimpleDateFormat dfDate_day;
		switch (command) {
		case 0:
<<<<<<< HEAD
			t2s.speakOut(1);
			retString = "next";
			break;
		case 1:
			t2s.speakOut(-1);
			retString= " previous";
			break;
		case 2:
			t2s.speakOut(0);
=======
			dfDate_day= new SimpleDateFormat("HH:mm:ss");
			retString = "next";
			break;
		case 1:
			dfDate_day = new SimpleDateFormat("dd/MM/yyyy");
			retString= " previous";
			break;
		case 2:
>>>>>>> Layout
			retString = "repeat";
			break;

		case 3:
			Log.d("checkern ", ""+killCommanded);
			killCommanded = true;
			break;

		default:
			break;
		}

		return retString;
	}
	private void processCommand(ArrayList<String> matchStrings){
		String response = "I'm sorry, Dave. I'm afraid I can't do that.";
<<<<<<< HEAD
		String[] words = matchStrings.get(0).split(" ");
		int maxStrings = words.length;
		Log.d("Maxstring", ""+maxStrings);
=======
		int maxStrings = matchStrings.size();
>>>>>>> Layout
		boolean resultFound = false;
		for(int i =0; i < VALID_COMMANDS.length && !resultFound;i++){
			Log.d("FOOOOR", ""+i);
			for(int j=0; j < maxStrings && !resultFound; j++){
				Log.d("FÅÅÅÅR ", ""+j);
<<<<<<< HEAD
				if(words[j].equals(VALID_COMMANDS[i]) ){
=======
				if(matchStrings.get(j).equals(VALID_COMMANDS[i]) ){
>>>>>>> Layout
					response = getResponse(i);
				}
			}
		}
		tv.setText(response);
		final String finalResponse = response;
		mHandler.post(new Runnable() {
			public void run() {
				tv.setText(finalResponse);
			}
		});
<<<<<<< HEAD
	}
=======

	}

>>>>>>> Layout
}