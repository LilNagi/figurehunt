package com.example.figurehunt;

import java.util.ArrayList;

public class Logic {
    private static final int MAX_FIGURE = 10 ;
    private ArrayList<EnemyFigure> figures;
    private MainFigure mainFigure;
    private CanvasView canvasView;
    private static int width;
    private static int height;
    public Logic(CanvasView canvasView, int w, int h){
        this.canvasView = canvasView;
        width = w;
        height = h;
        initMainFigure();
        initEnemyFigure();
    }

    private void initEnemyFigure() {
        SimpleFigure mainFigureArea = mainFigure.getFigureArea();
        figures = new ArrayList<EnemyFigure>();
        for (int i = 0; i < MAX_FIGURE; i++){
            EnemyFigure figure;
            do {
                figure = EnemyFigure.getRandomFigure();
            } while (figure.isInteresect(mainFigureArea));
            figures.add(figure);
        }
        calcAndSetFigureColor();
    }
    private void calcAndSetFigureColor(){
        for (EnemyFigure figure : figures) {
            figure.setEnemyOrFoodColorDependsOn(mainFigure);

        }
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    private void initMainFigure() {

        mainFigure= new MainFigure(width/2,height/2);
    }
    public void onDraw() {
        canvasView.drawFigure(mainFigure);
        for (EnemyFigure figure : figures) {
            canvasView.drawFigure(figure);
            
        }
    }

    public void onTouchEvent(int x, int y) {
        mainFigure.moveMainFigureWhenTouchAt(x,y);
        moveFigure();
        checkCollision();
    }

    private void checkCollision() {
        SimpleFigure figureForDel = null;
        for (EnemyFigure figure : figures) {
            if (mainFigure.isInteresect(figure)){
                if (figure.isSmallerThan(mainFigure)){
                    mainFigure.growRadius(figure);
                    figureForDel = figure;
                    calcAndSetFigureColor();
                    break;
                } else{
                    gameEnd("Вы проиграли");
                    return;
                }
            }

        }
        if (figureForDel != null){
            figures.remove(figureForDel);
        }
        if (figures.isEmpty()){
            gameEnd("Вы выйграли");
        }

    }

    private void gameEnd(String text) {
        canvasView.showMessage(text);
        mainFigure.initRadius();
        initEnemyFigure();
        canvasView.redraw();
    }

    private void moveFigure() {
        for (EnemyFigure figure : figures) {
            figure.moveOneStep();

        }
    }

}
