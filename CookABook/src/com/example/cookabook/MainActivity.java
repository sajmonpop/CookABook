package com.example.cookabook;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.AlertDialog;
import android.app.ListActivity;
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
	private static final int VOICE_RECOGNITION_REQUEST_CODE = 1001;
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

		//weekList  = new MyList();
		Button btn = (Button) findViewById(R.id.searchButton);
		speakBtn = (Button) findViewById(R.id.speakButton);
		EditText eText = (EditText) findViewById(R.id.searchBox);
		msTextMatches = (Spinner) findViewById(R.id.sNoOfMatches);
		Text2Speech tts = new Text2Speech(btn, eText, this);
		mlvTextMatches = (ListView) findViewById(R.id.weeklyList);
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

	/*public void speak(View view) {
		Log.d("MyApp","pre speak ()");
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		Log.d("MyApp","I am here");
		// Specify the calling package to identify your application
		intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
		Log.d("MyApp","I am here");
		// Display an hint to the user about what he should say.
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "How do you do");//eText.getText().toString());
		Log.d("MyApp","I am here");
		// Given an hint to the recognizer about what the user is going to say
		//There are two form of language model available
		//1.LANGUAGE_MODEL_WEB_SEARCH : For short phrases
		//2.LANGUAGE_MODEL_FREE_FORM  : If not sure about the words or phrases and its domain.
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
		Log.d("MyApp","post speak");
		// If number of Matches is not selected then return show toast message
		if (msTextMatches.getSelectedItemPosition() == AdapterView.INVALID_POSITION) {
			Toast.makeText(this, "Please select No. of Matches from spinner",
					Toast.LENGTH_SHORT).show();
			return;
		}

	}*/
	public void speak(View view) {
		Log.d("MyApp","pre speak ()");
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

		// Specify the calling package to identify your application
		intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass()
				.getPackage().getName());
		Log.d("MyApp","pre speak ()");
		// Display an hint to the user about what he should say.
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "tala för i helvete!");
		Log.d("MyApp","pre speak ()");
		// Given an hint to the recognizer about what the user is going to say
		//There are two form of language model available
		//1.LANGUAGE_MODEL_WEB_SEARCH : For short phrases
		//2.LANGUAGE_MODEL_FREE_FORM  : If not sure about the words or phrases and its domain.
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);

		// If number of Matches is not selected then return show toast message
		if (msTextMatches.getSelectedItemPosition() == AdapterView.INVALID_POSITION) {
			Toast.makeText(this, "Please select No. of Matches from spinner",
					Toast.LENGTH_SHORT).show();
			return;
		}

		int noOfMatches = Integer.parseInt(msTextMatches.getSelectedItem()
				.toString());
		// Specify how many results you want to receive. The results will be
		// sorted where the first result is the one with higher confidence.
		intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, noOfMatches);
		//Start the Voice recognizer activity for the result.
		startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
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
		super.onActivityResult(requestCode, resultCode, data);
	}
	/**
	 * Helper method to show the toast message
	 **/
	void showToastMessage(String message){
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}