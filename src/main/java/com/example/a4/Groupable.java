package com.example.a4;

import java.util.ArrayList;

public interface Groupable {

    boolean hasChildren();
    ArrayList<Groupable> getChildren();
    boolean contains(double x, double y);
    void move(double dx, double dy);
    double getLeft();
    double getRight();
    double getTop();
    double getBottom();
    Ship getShip();
    boolean inRect(Rectangle rect);

}
