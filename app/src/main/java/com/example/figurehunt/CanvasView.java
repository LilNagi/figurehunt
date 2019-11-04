package com.example.figurehunt;

import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.Toast;

public class CanvasView extends View implements ICanvasView{
    private static int height;
    private static int width;
    private Logic logic;
    private Paint paint;
    private Canvas canvas;
    private Toast toast;

    public CanvasView(Context context,AttributeSet attrs){
        super(context,attrs);
        initWaH(context);
        logic = new Logic(this, width, height);
        initPaint();
    }
    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    private void initWaH(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        width = point.x;
        height = point.y;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        this.canvas = canvas;
        logic.onDraw();
    }

    @Override
    public void drawFigure(SimpleFigure figure) {
        paint.setColor(figure.getColor());
        canvas.drawCircle(figure.getX(),figure.getY(),figure.getRadius(),paint);
    }

    @Override
    public void redraw() {
        invalidate();
    }

    @Override
    public void showMessage(String text) {
        if (toast != null){
            toast.cancel();
        }
        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0 , 0);
        toast.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_MOVE){
            logic.onTouchEvent(x,y);
        }
        invalidate();
        return true;

    }
}

