package com.example.figurehunt;

public class SimpleFigure {
    protected int x;
    protected int y;
    protected int radius;
    private int color;

    public SimpleFigure(int x,int y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getRadius(){
        return radius;
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public SimpleFigure getFigureArea() {
        return new SimpleFigure(x, y, radius * 3);
    }

    public boolean isInteresect(SimpleFigure circle) {
        return radius + circle.radius >= Math.sqrt(Math.pow(x - circle.x, 2) + Math.pow(y - circle.y,2));
    }
}
