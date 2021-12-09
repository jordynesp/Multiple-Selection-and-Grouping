package com.example.a4;

public class Rectangle {
    double left,top,right,bottom;
    double startX,startY;

    public Rectangle(double x, double y) {
        startX = x;
        startY = y;
        left = x;
        top = y;
        right = x;
        bottom = y;
    }

    public void resize(double x, double y) {
        left = Math.min(x, startX);
        right = Math.max(x, startX);
        top = Math.min(y, startY);
        bottom = Math.max(y, startY);
    }

    public boolean contains(double x, double y) {
        return x >= left && x <= right && y >= top && y <= bottom;
    }
}
