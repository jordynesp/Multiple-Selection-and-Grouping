package com.example.a4;

import java.util.ArrayList;
import java.util.Optional;

public class ShipModel {
    public ArrayList<Ship> ships;
    ArrayList<ShipModelSubscriber> subscribers;
    Rectangle rect;

    public ShipModel() {
        subscribers = new ArrayList<>();
        ships = new ArrayList<>();
    }

    public Ship createShip(double x, double y) {
        Ship s = new Ship(x,y);
        ships.add(s);
        notifySubscribers();
        return s;
    }

    public Optional<Ship> detectHit(double x, double y) {
        return ships.stream().filter(s -> s.contains(x, y)).reduce((first, second) -> second);
    }

    public void moveShip(Ship b, double dX, double dY) {
        b.moveShip(dX,dY);
        notifySubscribers();
    }

    public Rectangle getRect() {
        return rect;
    }

    public void createRect(double x, double y) {
        rect = new Rectangle(x, y);
        notifySubscribers();
    }

    public void resizeRect(double x, double y) {
        rect.resize(x, y);
        notifySubscribers();
    }

    public void removeRect() {
        rect = null;
        notifySubscribers();
    }

    public void addSubscriber (ShipModelSubscriber aSub) {
        subscribers.add(aSub);
    }

    private void notifySubscribers() {
        subscribers.forEach(sub -> sub.modelChanged());
    }
}
