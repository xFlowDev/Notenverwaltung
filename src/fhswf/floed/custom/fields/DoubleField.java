package fhswf.floed.custom.fields;

import javafx.scene.control.TextField;

public class DoubleField extends TextField {
    private double doubleValue;

    public DoubleField() {
        this.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                if (!newValue.matches("[\\d+\\.\\d+|\\d+]")) {
                    this.setText(newValue.replaceAll("[^\\d+\\.\\d+|\\d+]|\\+", ""));
                } else {
                    this.doubleValue = Double.parseDouble(newValue);
                }
            }
        });
    }

    public void setDoubleValue(double value) {
        this.setText(Double.toString(value));
        this.doubleValue = value;
    }

    public double getDoubleValue() {
        return this.doubleValue;
    }
}
