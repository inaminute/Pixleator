import javax.swing.*;
import java.awt.*;

public class Pixelator {

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        setupCanvas(resolution);
    }

    private static void setupCanvas(Dimension resolution) {
        JFrame frame = new JFrame("Pixelator");
        frame.setSize(resolution.width, resolution.height);
        frame.setExtendedState(frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        JLayeredPane layeredPane = frame.getLayeredPane();

        CoordsListener coordsListener = new CoordsListener();

        PopupPanel popupPanel = new PopupPanel();
        coordsListener.addObserver(popupPanel);
        PixelatorPanel mainPanel = new PixelatorPanel(resolution, coordsListener);
        mainPanel.setFocusable(true);

        layeredPane.add(mainPanel, new Integer(1));
        layeredPane.add(popupPanel, new Integer(2));

        frame.setVisible(true);

    }

}
