package fhswf.floed.window;

public class WindowSizeManager {
    private static double width = 1200;
    private static double height = 800;


    public static void setWidth(double newWidth) {
        width = newWidth;
    }

    public static double getWidth() {
        return width;
    }

    public static void setHeight(double newHeight) {
        height = newHeight;
    }

    public static double getHeight() {
        return height;
    }


}
