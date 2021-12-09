package com.example.a4;

public class Rectangle {
    double left,top,width,height;
    double startX,startY;

    public Rectangle(double x, double y) {
        startX = x;
        startY = y;
        left = x;
        top = y;
        width = x;
        height = y;
    }

    public void resize(double x, double y) {
        left = startX;
        top = startY;
        width = x - startX;
        height = y - startY;
        if (width < 0) {
            left = x;
            width = startX - x;
        }
        if (height < 0) {
            top = y;
            height = startY - y;
        }
    }

    public void move(double dX, double dY) {
        left += dX;
        width += dX;
        startX += dX;
        top += dY;
        height += dY;
        startY += dY;
    }

    public boolean contains(double x, double y) {
        return x >= left && x <= width && y >= top && y <= height;
    }

    public void resetStartCoords() {
        startX = left;
        startY = top;
    }
}
