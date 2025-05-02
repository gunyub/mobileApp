package kr.co.company.viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.Objects;

public class MyPagerAdapter extends PagerAdapter {

    Context context;
    int[] images;
    String[] descriptions;

    LayoutInflater layoutInflater;

    public MyPagerAdapter(Context context, int[] images, String[] descriptions) {
        this.context = context;
        this.images = images;
        this.descriptions = descriptions;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        // ✅ ViewPager가 정상 작동하려면 이렇게 써야 함
        return view == object;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.item, container, false);

        // 이미지 설정
        ImageView imageView = itemView.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);

        // 텍스트 설정
        TextView textView = itemView.findViewById(R.id.textView);
        textView.setText(descriptions[position]);

        container.addView(itemView); // ✅ 뷰 추가
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // ✅ 안전하게 object를 View로 캐스팅
        container.removeView((View) object);
    }
}
