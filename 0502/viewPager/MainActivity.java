package kr.co.company.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    // 이미지 배열
    int[] images = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3
    };

    // 이미지에 대응하는 텍스트 배열
    String[] descriptions = {
            "자동차",
            "강아지(스피치)",
            "강아지(리트리버)"
    };

    MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        myPagerAdapter = new MyPagerAdapter(MainActivity.this, images, descriptions);
        viewPager.setAdapter(myPagerAdapter);
    }
}

