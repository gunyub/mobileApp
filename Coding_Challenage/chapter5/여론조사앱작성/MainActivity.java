    package com.example.chapter5;

    import android.os.Bundle;
    import android.widget.ImageView;
    import android.widget.RadioButton;
    import android.widget.RadioGroup;
    import androidx.appcompat.app.AppCompatActivity;

    public class MainActivity extends AppCompatActivity {

        private ImageView imageView;
        private RadioGroup radioGroup;
        private RadioButton radioDog, radioCat;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            imageView = findViewById(R.id.imageView);
            radioGroup = findViewById(R.id.radioGroup);
            radioDog = findViewById(R.id.radioDog);
            radioCat = findViewById(R.id.radioCat);

            radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                if (checkedId == R.id.radioDog) {
                    imageView.setImageResource(R.drawable.dog); // res/drawable/dog.png
                } else if (checkedId == R.id.radioCat) {
                    imageView.setImageResource(R.drawable.cat); // res/drawable/cat.png
                }
            });
        }
    }
