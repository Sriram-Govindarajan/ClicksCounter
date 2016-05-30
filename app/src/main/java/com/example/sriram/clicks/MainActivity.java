package com.example.sriram.clicks;

        import android.content.SharedPreferences;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.preference.PreferenceManager;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.View;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.Button;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import java.util.Random;

        import static com.example.sriram.clicks.R.*;

public class MainActivity extends AppCompatActivity
{
    private RelativeLayout Layout;
    int count=0,x,y,i,j;
    SharedPreferences preference;
    int color[]={50,150,250};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(id.toolbar);
        setSupportActionBar(toolbar);
        preference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        count = preference.getInt("newcount", 0);
        x = preference.getInt("red", 0);
        y = preference.getInt("green", 0);
        Layout = (RelativeLayout) findViewById(id.layout);
        Layout.setBackgroundColor(Color.argb(255, x, y, 150 ));
        TextView mytextView=(TextView) findViewById(id.text);
        mytextView.setText(Integer.toString(count));
    }

    protected void onPause()
    {
        super.onPause();
        SharedPreferences.Editor editPrefs = preference.edit();
        editPrefs.putInt("newcount", count);
        editPrefs.putInt("red", x);
        editPrefs.putInt("green", y);
        editPrefs.putInt("blue", 150);
        editPrefs.commit();
    }

    public void onButtonClick1(View v)
    {
        TextView mytextView=(TextView) findViewById(id.text);
        count++;
        mytextView.setText(Integer.toString(count));
        Layout = (RelativeLayout) findViewById(id.layout);
        Random rand = new Random();
        i=rand.nextInt(2);
        j=rand.nextInt(2);
        x=color[i];
        y=color[j];
        Layout.setBackgroundColor(Color.argb(255, x, y, 150 ));
    }

    public void onButtonClick2(View v)
    {
        TextView mytextView=(TextView) findViewById(id.text);
        Layout = (RelativeLayout) findViewById(id.layout);
        count=0;
        mytextView.setText(Integer.toString(count));
        Layout.setBackgroundColor(Color.argb(255,255,255,255 ));
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        TextView mytextView = (TextView) findViewById(id.text);
        savedInstanceState.putCharSequence("myText",mytextView.getText());
        savedInstanceState.putInt("cl",x);
        savedInstanceState.putInt("c2",y);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        TextView mytextView=(TextView) findViewById(id.text);
        Layout = (RelativeLayout) findViewById(id.layout);
        CharSequence tText=savedInstanceState.getCharSequence("myText");
        x=savedInstanceState.getInt("cl",x);
        y=savedInstanceState.getInt("c2",y);
        Layout.setBackgroundColor(Color.argb(255,x,y,150 ));
        count = Integer.parseInt((String) tText);
        if (savedInstanceState != null)
        {
            mytextView.setText(Integer.toString(count));
        }
    }
}