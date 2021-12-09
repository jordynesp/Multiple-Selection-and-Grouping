package com.example.a4;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class ShipController {
    InteractionModel iModel;
    ShipModel model;
    double prevX, prevY;
    double dX, dY;

    protected enum State {
        READY, DRAGGING, CREATE_RECT, RESIZE_RECT
    }

    protected State currentState;

    public ShipController() {
        currentState = State.READY;
    }

    public void setInteractionModel(InteractionModel newModel) {
        iModel = newModel;
    }

    public void setModel(ShipModel newModel) {
        model = newModel;
    }

    public void handlePressed(double x, double y, MouseEvent event) {
        prevX = x;
        prevY = y;
        switch (currentState) {
            case READY -> {
                // context: on a ship?
                Optional<Ship> hit = model.detectHit(x, y);
                if (hit.isPresent()) {
                    // is control held down?
                    if (event.isControlDown()) {
                        // add/remove ship from selection
                        if (iModel.getSelected().contains(hit.get())) {
                            iModel.removeSelected(hit.get());
                        }
                        else {
                            iModel.addSelected(hit.get());
                            if (iModel.getSelected().size() > 0) {
                                currentState = State.DRAGGING;
                            }
                        }
                    }
                    else {
                        // on ship, so select
                        if (!iModel.getSelected().contains(hit.get())) {
                            iModel.clearSelection();
                            iModel.addSelected(hit.get());
                        }
                        currentState = State.DRAGGING;
                    }
                } else {
                    // on background - is Shift down?
                    if (event.isShiftDown()) {
                        // create ship
                        Ship newShip = model.createShip(x, y);
                        iModel.clearSelection();
                        iModel.addSelected(newShip);
                        currentState = State.DRAGGING;
                    } else {
                        // clear selection
                        iModel.clearSelection();
                        currentState = State.CREATE_RECT;
                    }
                }
            }
        }
        System.out.println("Current state is: " + currentState);
    }

    public void handleDragged(double x, double y, MouseEvent event) {
        dX = x - prevX;
        dY = y - prevY;
        prevX = x;
        prevY = y;
        switch (currentState) {
           case DRAGGING -> {
               iModel.selectedShips.forEach(ship -> model.moveShip(ship, dX, dY));
           }
            case CREATE_RECT -> {
                model.createRect(x, y);
                model.resizeRect(x, y);
                currentState = State.RESIZE_RECT;
            }
            case RESIZE_RECT -> {
               model.resizeRect(x, y);
            }
        }
        System.out.println("Current state is: " + currentState);
    }

    public void handleReleased(double x, double y, MouseEvent event) {
        switch (currentState) {
            case DRAGGING, CREATE_RECT -> {
                currentState = State.READY;
            }
            case RESIZE_RECT -> {
                for (Ship ship : model.ships) {
                    if (model.isContained(ship)) {
                        iModel.addSelected(ship);
                    }
                }
                model.removeRect();
                currentState = State.READY;
            }
        }
        System.out.println("Current state is: " + currentState);
    }

    public void handleKeyPressed(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
    }
}
