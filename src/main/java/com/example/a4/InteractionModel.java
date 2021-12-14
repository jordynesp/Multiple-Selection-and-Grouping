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

    public void addSubscriber(ShipModelSubscriber aSub) {
        subscribers.add(aSub);
    }

    private void notifySubscribers() {
        subscribers.forEach(sub -> sub.modelChanged());
    }
}
