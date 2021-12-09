package com.example.a4;

import java.util.ArrayList;

public class InteractionModel {
    ArrayList<ShipModelSubscriber> subscribers;
    ArrayList<Ship> selectedShips;

    public InteractionModel() {
        subscribers = new ArrayList<>();
        selectedShips = new ArrayList<>();
    }

    public void clearSelection() {
        selectedShips.clear();
        notifySubscribers();
    }

    public void setSelected(Ship newSelection) {
        if (!selectedShips.contains(newSelection)) {
            selectedShips.add(newSelection);
        }
        notifySubscribers();
    }

    public void removeSelected(Ship ship) {
        selectedShips.remove(ship);
        notifySubscribers();
    }

    public ArrayList<Ship> getSelected() {
        return selectedShips;
    }

    public void addSubscriber(ShipModelSubscriber aSub) {
        subscribers.add(aSub);
    }

    private void notifySubscribers() {
        subscribers.forEach(sub -> sub.modelChanged());
    }
}
