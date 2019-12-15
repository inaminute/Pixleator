import java.awt.*;

public class ColorPicker {

    static Color lineColor = Color.RED;
    static Color fontColor = Color.white;

    public static Color getLineColor() {
        return lineColor;
    }

    public static void setLineColor(Color lineColor) {
        ColorPicker.lineColor = lineColor;
    }

    public static Color getFontColor() {
        return fontColor;
    }

    public static void setFontColor(Color fontColor) {
        ColorPicker.fontColor = fontColor;
    }
}
