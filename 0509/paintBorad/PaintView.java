package com.example.paintboard;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PaintView extends View {
    private Path path = new Path();
    private Paint paint = new Paint();
    private List<Path> paths = new ArrayList<>();
    private List<Paint> paints = new ArrayList<>();

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null); // 지우개를 위해 필요
        initPaint();
    }

    private void initPaint() {
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void setColor(int color) {
        paint.setXfermode(null);  // 일반 그리기 복귀
        paint.setColor(color);
    }

    public void setStrokeWidth(float width) {
        paint.setStrokeWidth(width);
    }

    public void enableEraser() {
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < paths.size(); i++) {
            canvas.drawPath(paths.get(i), paints.get(i));
        }
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path = new Path();
                path.moveTo(event.getX(), event.getY());
                paints.add(new Paint(paint)); // 현재 설정 복사
                paths.add(path);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(), event.getY());
                break;
        }
        invalidate();
        return true;
    }
}
