package com.fuchsiateam.fishboy;

import android.widget.TextView;
import android.util.Log;

import com.fuchsiateam.framework.Screen;
import com.fuchsiateam.framework.impl.AndroidGame;

public class FishboyActivity extends AndroidGame {
	StringBuilder builder = new StringBuilder();
	TextView textView;
	
	public void log(String text){
		Log.d("FishboyActivity", text);
		builder.append(text);
		builder.append('\n');
		textView.setText(builder.toString());	
	}


	@Override
	public Screen getStartScreen() {
		// TODO Auto-generated method stub
		return new LoadingScreen(this);
	}

    
}
