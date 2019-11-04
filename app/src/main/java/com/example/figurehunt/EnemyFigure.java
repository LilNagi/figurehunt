package com.example.figurehunt;

import android.graphics.Color;
import android.util.Log;

import java.util.Random;

public class EnemyFigure extends SimpleFigure {
    private static final int FROM_RADIUS = 10;
    private static final int TO_RADIUS = 110;
    private static final int E_COLOR = Color.RED;
    private static final int F_COLOR = Color.GRAY;
    private static final int R_SPEED = 10;
    private int dx;
    private int dy;

    public EnemyFigure(int x, int y, int radius, int dx, int dy) {

        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;
    }

    public static EnemyFigure getRandomFigure() {
        Random random = new Random();
        int x = random.nextInt(Logic.getWidth());
        int y = random.nextInt(Logic.getHeight());
        int dx = -R_SPEED + random.nextInt(R_SPEED*2);
        int dy = -R_SPEED + random.nextInt(R_SPEED*2);
        int radius = FROM_RADIUS + random.nextInt(TO_RADIUS - FROM_RADIUS);
        EnemyFigure enemyFigure = new EnemyFigure(x,y, radius, dx, dy);
        return enemyFigure;
    }

    public void setEnemyOrFoodColorDependsOn(MainFigure mainFigure) {
        if (isSmallerThan(mainFigure)){
            setColor(F_COLOR);
        }else {
            setColor(E_COLOR);
        }
    }

    public boolean isSmallerThan(SimpleFigure figure) {
        if (radius < figure.radius){
            return true;
        }
        return false;
    }

    public void moveOneStep() {
        x += dx;
        y += dy;
        checkBounds();
    }

    private void checkBounds() {
        if (x > Logic.getWidth() || x < 0){
            dx = -dx;
        }
        if (y > Logic.getHeight() || y < 0){
            dy = -dy;
        }
    }
}
