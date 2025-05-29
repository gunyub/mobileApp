package com.example.chapter9;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class SnowView extends View {
    private int snowCount = 100;
    private Snowflake[] snowflakes;
    private Bitmap background;
    private Random random = new Random();

    public SnowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background); // background.jpg
        generateSnowflakes(snowCount);
    }

    public void setSnowCount(int count) {
        this.snowCount = count;
        generateSnowflakes(count);
        invalidate();
    }

    private void generateSnowflakes(int count) {
        snowflakes = new Snowflake[count];
        for (int i = 0; i < count; i++) {
            snowflakes[i] = new Snowflake(getWidth(), getHeight(), random);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 배경 그리기
        if (background != null) {
            canvas.drawBitmap(background, null, new Rect(0, 0, getWidth(), getHeight()), null);
        }

        // 눈 내리기
        for (Snowflake flake : snowflakes) {
            flake.draw(canvas);
            flake.update(getHeight());
        }

        postInvalidateDelayed(30); // 재귀호출로 애니메이션
    }

    private static class Snowflake {
        float x, y, radius, speed;
        int width;
        Random random;

        public Snowflake(int width, int height, Random random) {
            this.random = random;
            this.width = width;
            x = random.nextInt(width);
            y = random.nextInt(height);
            radius = random.nextInt(10) + 5;
            speed = radius / 4 + 2;
        }

        void update(int height) {
            y += speed;
            if (y > height) {
                y = 0;
                x = random.nextInt(width);
            }
        }

        void draw(Canvas canvas) {
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setAntiAlias(true);
            canvas.drawCircle(x, y, radius, paint);
        }
    }
}
