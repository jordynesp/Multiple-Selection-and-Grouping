package com.example.a4;

import java.util.ArrayList;

public class InteractionModel {
    ArrayList<ShipModelSubscriber> subscribers;
    ArrayList<Groupable> selectedShips;

    public InteractionModel() {
        subscribers = new ArrayList<>();
        selectedShips = new ArrayList<>();
    }

    public void clearSelection() {
        selectedShips.clear();
        notifySubscribers();
    }

    public void addSelected(Groupable newSelection) {
        if (!selectedShips.contains(newSelection)) {
            selectedShips.add(newSelection);
        }
        notifySubscribers();
    }

    public void removeSelected(Groupable ship) {
        selectedShips.remove(ship);
        notifySubscribers();
    }

    public ArrayList<Groupable> getSelected() {
        return selectedShips;
    }

    public ArrayList<Ship> getSelectedShips() {
        ArrayList<Ship> ships = new ArrayList<>();
        getShipHelper(ships, selectedShips);
        return ships;
    }

    private void getShipHelper(ArrayList<Ship> ships, ArrayList<Groupable> groups) {
        for (Groupable child : groups) {
            if (!child.hasChildren()) {
                ships.add(child.getShip());
            }
            else {
                getShipHelper(ships, child.getChildren());
            }
        }
    }

    public void addSubscriber(ShipModelSubscriber aSub) {
        subscribers.add(aSub);
    }

    private void notifySubscribers() {
        subscribers.forEach(ShipModelSubscriber::modelChanged);
    }
}
