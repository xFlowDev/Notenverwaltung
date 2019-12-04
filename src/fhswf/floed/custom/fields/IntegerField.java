package fhswf.floed.custom.fields;

import javafx.scene.control.TextField;

public class IntegerField extends TextField {

    private int integerValue;

    public IntegerField() {
        this.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                if (!newValue.matches("\\d*")) {
                    this.setText(newValue.replaceAll("[^\\d]", ""));
                } else {
                    this.integerValue = Integer.parseInt(newValue);
                }
            }
        });
    }

    public void setIntegerValue(int value) {
        this.setText(Integer.toString(value));
        this.integerValue = value;
    }

    public int getIntegerValue() {
        return this.integerValue;
    }
}
