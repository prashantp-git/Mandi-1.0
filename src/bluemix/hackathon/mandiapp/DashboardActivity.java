package bluemix.hackathon.mandiapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class DashboardActivity extends Activity {
	private Spinner commSpinner;
	private Spinner stateSpinner;
	private Spinner districtSpinner;

	private Button button;
	private Button profileButton;
	private String string;
	private EditText response;

	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard_layout);
		context = this;

		button = (Button) findViewById(R.id.button1);
		profileButton = (Button) findViewById(R.id.profileButton);
		commSpinner = (Spinner) findViewById(R.id.commSpinner);
		stateSpinner = (Spinner) findViewById(R.id.stateSpinner);
		districtSpinner = (Spinner) findViewById(R.id.districtwiseSpinner);
		addItemsOnSpinner();
		response = (EditText) findViewById(R.id.responseTextView);
		response.setVisibility(View.GONE);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Log.e("onclick()", "onClick");
				new RetrieveFeedTask().execute();
			}
		});
		profileButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new AlertDialog.Builder(context)
						.setTitle("Logout")
						.setMessage("Do you want to logout?")
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										// TODO Auto-generated method stub
										Intent mIntent = new Intent(context,
												LoginSignUpActivity.class);
										startActivity(mIntent);
										finish();

									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										// TODO Auto-generated method stub

									}
								}).setIcon(android.R.drawable.ic_dialog_alert)
						.show();
			}
		});

	}

	public void addItemsOnSpinner() {

		List<String> list = new ArrayList<String>();
		list.add("Select a commodity");
		list.add("Maize");
		list.add("Coconut");
		list.add("Potato");
		list.add("Apple");
		list.add("Mustard");
		list.add("Bengal Grams");
		list.add("Banana");
		list.add("Sugar");
		list.add("Bottle Gourd");

		List<String> liststate = new ArrayList<String>();
		liststate.add("Select a state");
		liststate.add("Rajasthan");
		liststate.add("Tamil Nadu");
		liststate.add("Uttar Pradesh");
		liststate.add("Punjab");
		liststate.add("Gujarat");
		liststate.add("Kerala");
		liststate.add("Orissa");
		liststate.add("Himachal Pradesh");
		liststate.add("Maharashtra");

		List<String> listDistrict = new ArrayList<String>();
		listDistrict.add("Select a district");
		listDistrict.add("Rajasamand");
		listDistrict.add("Sirohi");

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		commSpinner.setAdapter(dataAdapter);

		ArrayAdapter<String> dataAdapterState = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, liststate);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		stateSpinner.setAdapter(dataAdapterState);

		ArrayAdapter<String> dataAdapterDistrict = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, listDistrict);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		districtSpinner.setAdapter(dataAdapterDistrict);
	}

	class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

		protected void onPreExecute() {
		}

		protected String doInBackground(Void... urls) {
			try {

				Log.e("onclick", "URL");
				URL url = new URL(
						"https://www.data.gov.in/api/datastore/resource.json?resource_id=9ef84268-d588-465a-a308-a864a43d0070&api-key=a49052a686f811bc778da5c1f5d84f15");
				HttpURLConnection urlConnection = (HttpURLConnection) url
						.openConnection();
				urlConnection.connect();

				Log.e("onclick", "Connection");
				try {
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(
									urlConnection.getInputStream()));
					StringBuilder stringBuilder = new StringBuilder();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						stringBuilder.append(line).append("\n");
						Log.d("onclick", line);
					}
					bufferedReader.close();
					return stringBuilder.toString();
				} finally {
					urlConnection.disconnect();
					//String abc = getProfile();
					//return abc;
				}

			} catch (Exception e) {
				Log.e("ERROR", e.getMessage(), e);
				return null;
			}
		}

		protected void onPostExecute(String response) {
			if (response == null) {
				response = "THERE WAS AN ERROR";
			}
			JSONArray mArray;
			try {
				DashboardActivity.this.response.setVisibility(View.VISIBLE);
				DashboardActivity.this.response.append(response);

			/*	mArray = new JSONArray(response);
				for (int i = 0; i < mArray.length(); i++) {
					JSONObject mJsonObject = mArray.getJSONObject(i);
					Log.d("OutPut", mJsonObject.toString());
					// DashboardActivity.this.response.append(mJsonObject.getString("commodity"));
				}
				*/

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public String getProfile() {
		BufferedReader br = null;
		String fname = "dummy";
		StringBuilder stringBuilder = new StringBuilder();
		try {
			String fpath = Environment.getExternalStorageDirectory().getPath()
					+ "/" + fname + ".txt"; /* File Path */
			br = new BufferedReader(new FileReader(fpath));
			String line = "";

			while ((line = br.readLine()) != null) {
				stringBuilder.append(line).append("\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
}
