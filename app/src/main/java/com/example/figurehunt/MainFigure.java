package com.example.figurehunt;

import android.graphics.Color;

public class MainFigure extends SimpleFigure {
    public static final int INIT_RADIUS = 50;
    private static final int M_SPEED = 50;
    private static final int M_COLOR = Color.GREEN;

    public MainFigure(int x, int y) {
        super(x, y, INIT_RADIUS );
        setColor(M_COLOR);
    }

    public void moveMainFigureWhenTouchAt(int x1, int y1) {
        int dx = (x1-x) * M_SPEED / Logic.getWidth();
        int dy = (y1-y) * M_SPEED / Logic.getHeight();
        x += dx;
        y += dy;
    }

    public void initRadius() {
        radius = INIT_RADIUS;
    }

    public void growRadius(EnemyFigure figure) {
        radius = (int) Math.sqrt(Math.pow(radius, 2 )+ Math.pow (figure.radius,2));
    }
}
