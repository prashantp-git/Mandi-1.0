package bluemix.hackathon.mandiapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginSignUpActivity extends Activity {

	private EditText usernameEditText;
	private EditText passwordEditText;
	private Button loginButton;
	private Button signupButton;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_signup_layout);
		mContext = this;
		usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		loginButton = (Button) findViewById(R.id.loginButton);
		signupButton = (Button) findViewById(R.id.signupButton);
		loginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String username = usernameEditText.getText().toString();
				String password = passwordEditText.getText().toString();
				if(username.equals("farmer") && password.equals("farmer"))
				{
					Intent mIntent = new Intent(mContext,DashboardActivity.class);
					startActivity(mIntent);
					finish();
				}
				else
				{
					Toast.makeText(mContext, "Check email or password.", Toast.LENGTH_LONG).show();
					usernameEditText.setText("");
					passwordEditText.setText("");
				}

			}
		});
		signupButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent mIntent = new Intent(mContext,SignUpActivity.class);
				startActivity(mIntent);
				finish();
			}
		});
	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.main, menu); return true; }
	 */

}
