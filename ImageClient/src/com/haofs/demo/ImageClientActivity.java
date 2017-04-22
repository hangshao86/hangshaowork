package com.haofs.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class ImageClientActivity extends Activity implements OnClickListener {
	private Button button;
	private EditText editText;
	private WebView webView;
	

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button = (Button) findViewById(R.id.button1);
		editText = (EditText) findViewById(R.id.editText);
		webView = (WebView) findViewById(R.id.webView);

		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		webView.loadUrl(editText.getText().toString());
	}
}