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

public class SignUpActivity extends Activity {
	private EditText fullnameEditText;
	private EditText emailEditText;
	private EditText mobEditText;
	private EditText usernameEditText;
	private EditText passwordEditText;

	private String fullnameText;
	private String emailText;
	private String mobText;
	private String usernameText;
	private String passwordText;

	private Button signupButton;
	private Button loginButton;
	private Button backButton;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup_layout);
		mContext = this;
		fullnameEditText = (EditText) findViewById(R.id.fullnameEditText);
		emailEditText = (EditText) findViewById(R.id.emailEditText);
		mobEditText = (EditText) findViewById(R.id.mobEditText);
		usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		signupButton = (Button) findViewById(R.id.signupButton);
		loginButton = (Button) findViewById(R.id.loginButton);
		backButton = (Button) findViewById(R.id.arrowbackButton);
		signupButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				fullnameText = fullnameEditText.getText().toString();
				emailText = emailEditText.getText().toString();
				mobText = mobEditText.getText().toString();
				usernameText = usernameEditText.getText().toString();
				passwordText = passwordEditText.getText().toString();

				if (fullnameText.equals("") || emailText.equals("")
						|| mobText.equals("") || usernameText.equals("")
						|| passwordText.equals("")) {
					Toast.makeText(mContext, "Item cannot be empty",
							Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(mContext, "Account created",
							Toast.LENGTH_LONG).show();
					Intent mIntent = new Intent(mContext,
							LoginSignUpActivity.class);
					startActivity(mIntent);
					finish();
				}
			}
		});
		loginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent mIntent = new Intent(mContext,
						LoginSignUpActivity.class);
				startActivity(mIntent);
				finish();
			}
		});
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent mIntent = new Intent(mContext,
						LoginSignUpActivity.class);
				startActivity(mIntent);
				finish();
			}
		});
	}
}
