import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class PixelatorPanel extends JPanel {
    Point pointStart= null, pointEnd = null;

    boolean shiftPressed = false;

    private CoordsListener listener;

    public PixelatorPanel(Dimension resolution, CoordsListener cListener) {
        super(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setBounds(0, 0, resolution.width, resolution.height);
        this.add(getScreenshot(resolution));
        this.listener = cListener;
    }

    private JLabel getScreenshot(Dimension resolution) {
        Robot bot = null;
        try {
            bot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Rectangle capture = new Rectangle(resolution);
        BufferedImage image = bot.createScreenCapture(capture);
        JLabel label = new JLabel(new ImageIcon(image));
        return label;

    }

    //Event Listeners
    {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pointStart = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pointStart = null;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                pointEnd = e.getPoint();
                repaint();

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                pointEnd = e.getPoint();
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                String keyName = KeyEvent.getKeyText(e.getKeyCode());
                if (keyName == "Shift") {
                    shiftPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String keyName = KeyEvent.getKeyText(e.getKeyCode());
                if (keyName == "Shift") {
                    shiftPressed = false;
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (pointStart != null && pointEnd != null) {
            int x1 = pointStart.x;
            int y1 = pointStart.y;
            int x2 = pointEnd.x;
            int y2 = pointEnd.y;

            drawStartCrosshair(g, x1, y1);

            g.setColor(ColorPicker.getLineColor());

            if (shiftPressed) {
                //Force strict line

                int dx = getXDiff(x1, x2);
                int dy = getYDiff(y1, y2);

                if (dy < 0) {
                    drawPixelScale(g, x1, y1, x1, y2);
                } else if (dx > dy || dx < 0) {
                    drawPixelScale(g, x1, y1, x2, y1);
                } else {
                    drawPixelScale(g, x1, y1, x1, y2);
                }
            }else{
                drawPixelScale(g, x1, y1, x2, y2);
            }

        }
    }

    private void drawPixelScale(Graphics g, int ix, int iy, int ex, int ey) {
        g.drawLine(ix, iy, ex, ey);
        drawEndCrosshair(g, ex, ey);
        pixelDifference(g, ix, iy, ex, ey);
    }

    private void pixelDifference(Graphics g, int x1, int y1, int x2, int y2) {
        int xDiff = getXDiff(x1, x2);
        int yDiff = getXDiff(y1, y2);

        int xPos = (x2 + x1) / 2;
        int yPos = (y2 + y1) / 2;

        listener.setPosition(x1, y1, x2, y2);
        g.drawString("W: " + xDiff + "H: " + yDiff, xPos - 20, yPos);

    }

    private void drawEndCrosshair(Graphics g, int ex, int ey) {
        g.setColor(ColorPicker.getLineColor());
        g.fillRect(ex - 14, ey, 30, 1);
        g.fillRect(ex, ey - 14, 1, 30);
        g.setColor(ColorPicker.getFontColor());
        String sX2 = Integer.toString(ex);
        String sY2 = Integer.toString(ey);
        g.drawString("Y2: " + sY2, ex - 20, ey - 30);
        g.drawString("X2: " + sX2, ex - 20, ey - 15);


    }

    public static int getXDiff(int x1, int x2) {
        int xDiff = x2 - x1;
        return xDiff;
    }

    public static int getYDiff(int y1, int y2) {
        int yDiff = y2 - y1;
        return yDiff;
    }

    private void drawStartCrosshair(Graphics g, int ix, int iy) {
        g.setColor(ColorPicker.getLineColor());
        g.fillRect(ix - 14, iy, 30, 1);
        g.fillRect(ix, iy - 14, 1, 30);

        g.setColor(ColorPicker.getFontColor());
        String sX = Integer.toString(ix);
        String sY = Integer.toString(iy);

        g.drawString("Y1: " + sY, ix - 20, iy - 30);
        g.drawString("X1: " + sX, ix - 20, iy - 15);
    }
}
