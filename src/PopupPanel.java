import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class PopupPanel extends JPanel implements Observer {
    private int mouseX = 0;
    private int mouseY = 0;
    private int panelX = 0;
    private int panelY = 0;

    private int iX = 0, iY = 0, eX = 0, eY = 0;

    private JLabel ciX = new JLabel("0");
    private JLabel ciY = new JLabel("0");
    private JLabel ceX = new JLabel("0");
    private JLabel ceY = new JLabel("0");
    private JLabel widthLabel = new JLabel("0");
    private JLabel heightLabel = new JLabel("0");

    private CoordsListener coordsListener;

    public PopupPanel() {
        setLayout(null);
        setBounds(100, 50, 200, 200);
        setBorder(BorderFactory.createTitledBorder("Info Panel"));
        add(getLabel("X", 75, 20));
        add(getLabel("Y", 130, 20));
        add(getLabel("Start:", 10, 42));
        add(getLabel("End:", 10, 67));
        add(getSeparator("_______________________", 18, 80));
        add(getLabel("Width:", 40, 100));
        add(getLabel("Height:", 120, 100));
        add(getSeparator("_______________________", 18, 125));
        add(getLabel("Set Line Color:", 10, 147));
        add(getLabel("Set Font Color:", 10, 172));

        add(addLineColorButton());
        add(addFontColorButton());
    }

    {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getXOnScreen();
                mouseY = e.getYOnScreen();

                panelX = getX();
                panelY = getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int dx = e.getXOnScreen()-mouseX;
                int dy = e.getYOnScreen()-mouseY;

                int posX = panelX + dx;
                int posY = panelY + dy;

                setLocation(posX, posY);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

    }

    //Observer Pattern
    public void update(Observable o, Object object) {
        coordsListener = (CoordsListener) o;
        iX = coordsListener.getIx();
        iY = coordsListener.getIy();
        eX = coordsListener.getEx();
        eY = coordsListener.getEy();

        updateSx(iX, 65, 42);
        updateSy(iY, 125, 42);
        updateEx(eX, 65, 67);
        updateEy(eY, 125, 67);


        updateWidth(PixelatorPanel.getXDiff(iX, eX), 45, 115);
        updateHeight(PixelatorPanel.getYDiff(iY, eY), 126, 115);

    }

    private void updateSx(int val, int x, int y) {
        ciX.setText(Integer.toString(val));
        Dimension size = ciX.getPreferredSize();
        ciX.setBounds(x, y, size.width, size.height);
        add(ciX);
    }
    private void updateSy(int val, int x, int y) {
        ciY.setText(Integer.toString(val));
        Dimension size = ciY.getPreferredSize();
        ciY.setBounds(x, y, size.width, size.height);
        add(ciY);
    }
    private void updateEx(int val, int x, int y) {
        ceX.setText(Integer.toString(val));
        Dimension size = ceX.getPreferredSize();
        ceX.setBounds(x, y, size.width, size.height);
        add(ceX);
    }
    private void updateEy(int val, int x, int y) {
        ceY.setText(Integer.toString(val));
        Dimension size = ceY.getPreferredSize();
        ceY.setBounds(x, y, size.width, size.height);
        add(ceY);
    }

    private void updateWidth(int val, int x, int y) {
        widthLabel.setText(Integer.toString(val));
        Dimension size = widthLabel.getPreferredSize();
        widthLabel.setBounds(x, y, size.width, size.height);
        add(widthLabel);
    }

    private void updateHeight(int val, int x, int y) {
        heightLabel.setText(Integer.toString(val));
        Dimension size = heightLabel.getPreferredSize();
        heightLabel.setBounds(x, y, size.width, size.height);
        add(heightLabel);
    }

    private JButton addLineColorButton() {
        JButton lineColorBtn = new JButton("Select");
        lineColorBtn.setBounds(100, 145, 80, 20);
        lineColorBtn.setFocusable(false);
        lineColorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color lineColor = JColorChooser.showDialog(null, "Set Line Color", ColorPicker.getLineColor());
                ColorPicker.setLineColor(lineColor);
            }
        });
        return lineColorBtn;

    }

    private JButton addFontColorButton() {
        JButton fontColorBtn = new JButton("Select");
        fontColorBtn.setBounds(100, 170, 80, 20);
        fontColorBtn.setFocusable(false);
        fontColorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color fontColor = JColorChooser.showDialog(null, "Set Font Color", ColorPicker.getFontColor());
                ColorPicker.setFontColor(fontColor);
            }
        });
        return fontColorBtn;

    }

    private JLabel getSeparator(String value, int x, int y) {
        JLabel label = new JLabel(value);
        Dimension size = label.getPreferredSize();
        label.setBounds(x, y, size.width, size.height);
        label.setForeground(Color.RED);
        return label;
    }

    private JLabel getLabel(String value, int x, int y) {
        JLabel label = new JLabel(value);
        Dimension size = label.getPreferredSize();
        label.setBounds(x, y, size.width, size.height);
        return label;
    }

}


