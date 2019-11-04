package com.example.figurehunt;

public interface ICanvasView {
    void drawFigure(SimpleFigure figure);

    void redraw();

    void showMessage(String text);
}
