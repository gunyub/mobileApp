package com.example.checkbox5;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import com.example.checkbox5.R;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    ImageView imageview1, imageview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageview1 = (ImageView)findViewById(R.id.imageView1);
        imageview2 = (ImageView)findViewById(R.id.imageView2);
    }
    public void onCheckboxClicked(View v) {
        boolean checked = ((CheckBox) v).isChecked();

        if (v.getId() == R.id.checkbox1) {
            if (checked) imageview1.setImageResource(R.drawable.sand1);
            else imageview1.setImageResource(0);
        } else if (v.getId() == R.id.checkbox2) {
            if (checked) imageview2.setImageResource(R.drawable.sand2);
            else imageview2.setImageResource(0);
        }
    }

}