package fhswf.floed.custom.fields;

import javafx.scene.control.TextField;

public class FloatField extends TextField {
    private float floatValue;

    public FloatField() {
        this.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                if (!newValue.matches("[\\d+\\.\\d+|\\d+]")) {
                    this.setText(newValue.replaceAll("[^\\d+\\.\\d+|\\d+]|\\+", ""));
                } else {
                    this.floatValue = Float.parseFloat(newValue);
                }
            }
        });
    }

    public void setFloatValue(float value) {
        this.setText(Float.toString(value));
        this.floatValue = value;
    }

    public float getFloatValue() {
        return this.floatValue;
    }
}
