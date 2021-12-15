package com.example.a4;

import java.util.ArrayList;
import java.util.Optional;

public class ShipModel {
    public ArrayList<Groupable> ships;
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

    public Optional<Groupable> detectHit(double x, double y) {
        return ships.stream().filter(s -> s.contains(x, y)).reduce((first, second) -> second);
    }

    public void moveShip(Groupable ship, double dX, double dY) {
        ship.move(dX,dY);
        notifySubscribers();
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

    public ShipGroup group(ArrayList<Groupable> selection) {
        ShipGroup newGroup = new ShipGroup();
        selection.forEach(newGroup::addChild);
        newGroup.getChildren().forEach(ship -> ships.remove(ship));
        ships.add(newGroup);
        return newGroup;
    }

    public boolean isContained(Groupable ship) {
        return ship.inRect(rect);
    }

    public void addSubscriber (ShipModelSubscriber aSub) {
        subscribers.add(aSub);
    }

    private void notifySubscribers() {
        subscribers.forEach(ShipModelSubscriber::modelChanged);
    }
}
