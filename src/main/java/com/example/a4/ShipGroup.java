package com.example.a4;

import java.util.ArrayList;

public class ShipGroup implements Groupable {
    ArrayList<Groupable> children;
    double top, left, bottom, right;

    public ShipGroup() {
        children = new ArrayList<>();
    }

    void addChild(Groupable child) {
        children.add(child);
        updateBounds();
    }

    @Override
    public boolean hasChildren() {
        return true;
    }

    @Override
    public ArrayList<Groupable> getChildren() {
        return children;
    }

    @Override
    public boolean contains(double x, double y) {
        return children.stream().anyMatch(child -> child.contains(x, y));
    }

    @Override
    public void move(double dx, double dy) {
        children.forEach(child -> child.move(dx, dy));
        updateBounds();
    }


    @Override
    public double getLeft() {
        ArrayList<Double> lefts = new ArrayList<>();
        getLeftHelper(lefts, children);
        return lefts.stream().min(Double::compare).get();
    }

    private void getLeftHelper(ArrayList<Double> lefts, ArrayList<Groupable> groups) {
        for (Groupable child : groups) {
            if (!child.hasChildren()) {
                lefts.add(child.getLeft());
            }
            else {
                getLeftHelper(lefts, child.getChildren());
            }
        }
    }

    @Override
    public double getRight() {
        return 0;
    }

    @Override
    public double getTop() {
        ArrayList<Double> tops = new ArrayList<>();
        getTopHelper(tops, children);
        return tops.stream().min(Double::compare).get();
    }

    private void getTopHelper(ArrayList<Double> tops, ArrayList<Groupable> groups) {
        for (Groupable child : groups) {
            if (!child.hasChildren()) {
                tops.add(child.getTop());
            }
            else {
                getTopHelper(tops, child.getChildren());
            }
        }
    }

    @Override
    public double getBottom() {
        return 0;
    }

    @Override
    public Ship getShip() {
        return null;
    }

    @Override
    public boolean inRect(Rectangle rect) {
        return children.stream().allMatch(child -> child.inRect(rect));
    }

    private void updateBounds() {
        left = getLeft();
    }
}
