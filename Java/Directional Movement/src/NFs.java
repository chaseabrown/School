import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class NFs {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Need for Speed");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new startGame());
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}

class startGame extends JPanel {

    private int x = 0, y = 0, velX = 0, velY = 0, width = 800, height = 600;
    private BufferedImage transformedImage;
    private Timer time = new Timer(5, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (x < 0) {
                velX = 0;
                x = 0;
            }
            if (y < 0) {
                velY = 0;
                y = 0;
            }
            if (x > width) {
                velX = 0;
                x = width;
            }
            if (y > height) {
                velY = 0;
                y = height;
            }
            x = x + velX;
            y = y + velY;
            repaint();
        }
    });

    public startGame() {

        transformedImage = createTransformedImage(createCar(), 45);

        addKeyBindings();
        time.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D ga = (Graphics2D) g;

        ga.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        ga.drawImage(transformedImage, x, y, null);
    }
    //Simply used for testing (to simulate sprites) 

    public static BufferedImage createCar() {
        BufferedImage img = new BufferedImage(100, 100, BufferedImage.TRANSLUCENT);
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.GREEN);
        g2.fillRect(0, 0, img.getWidth(), img.getHeight());
        g2.dispose();
        return img;
    }

    //http://stackoverflow.com/questions/4156518/rotate-an-image-in-java
    public static BufferedImage createTransformedImage(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);
        BufferedImage result = new BufferedImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g2d = result.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate((neww - w) / 2, (newh - h) / 2);
        g2d.rotate(angle, w / 2, h / 2);
        g2d.drawRenderedImage(image, null);
        g2d.dispose();
        return result;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    private void addKeyBindings() {
        AbstractAction onReleaseAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                velX = 0;
                velY = 0;
            }
        };

        //we are adding multiple keybindigs with same focus map etc to same component so we intitae an instance of class rate than use static methods
        KeyBinding kb = new KeyBinding(this, KeyBinding.WHEN_IN_FOCUSED_WINDOW);

        kb.addKeyBindingOnPressAndRelease(KeyEvent.VK_DOWN, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                velX = 0;
                velY = 1;
            }
        }, "down pressed", onReleaseAction, "down released");

        kb.addKeyBindingOnPressAndRelease(KeyEvent.VK_UP, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                velX = 0;
                velY = -1;
            }
        }, "up pressed", onReleaseAction, "up released");

        kb.addKeyBindingOnPressAndRelease(KeyEvent.VK_LEFT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                velX = -1;
                velY = 0;
            }
        }, "left pressed", onReleaseAction, "left released");
        kb.addKeyBindingOnPressAndRelease(KeyEvent.VK_RIGHT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                velX = 1;
                velY = 0;
            }
        }, "right pressed", onReleaseAction, "right released");
    }
}

class KeyBinding {

    private final JComponent container;
    private final int inputMap;
    public static final int WHEN_IN_FOCUSED_WINDOW = JComponent.WHEN_IN_FOCUSED_WINDOW,
            WHEN_FOCUSED = JComponent.WHEN_FOCUSED,
            WHEN_ANCESTOR_OF_FOCUSED_COMPONENT = JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;

    public KeyBinding(JComponent container, int inputMap) {
        this.container = container;
        this.inputMap = inputMap;
    }

    public void addKeyBinding(int key, boolean onRelease, AbstractAction keybindingAction, String description) {
        putKeyBinding(container, inputMap, key, onRelease, keybindingAction, description);
    }

    public void addKeyBindingOnPress(int key, AbstractAction keybindingAction, String description) {
        putKeyBinding(container, inputMap, key, false, keybindingAction, description);
    }

    public void addKeyBindingOnRelease(int key, AbstractAction keybindingAction, String description) {
        putKeyBinding(container, inputMap, key, true, keybindingAction, description);
    }

    public void addKeyBindingOnPressAndRelease(int key, AbstractAction onPressAction, String onPressDesc, AbstractAction onReleaseAction, String onReleaseDesc) {
        putKeyBinding(container, inputMap, key, false, onPressAction, onPressDesc);
        putKeyBinding(container, inputMap, key, true, onReleaseAction, onReleaseDesc);
    }

    public static void putKeyBinding(JComponent container, int inputMap, int key, boolean onRelease, AbstractAction keybindingAction, String description) {
        container.getInputMap(inputMap).put(KeyStroke.getKeyStroke(key, 0, onRelease), description);
        container.getActionMap().put(description, keybindingAction);
    }

    public static void putKeyBindingOnPress(JComponent container, int inputMap, int key, AbstractAction keybindingAction, String description) {
        container.getInputMap(inputMap).put(KeyStroke.getKeyStroke(key, 0, false), description);
        container.getActionMap().put(description, keybindingAction);
    }

    public static void putKeyBindingOnRelease(JComponent container, int inputMap, int key, AbstractAction keybindingAction, String description) {
        container.getInputMap(inputMap).put(KeyStroke.getKeyStroke(key, 0, true), description);
        container.getActionMap().put(description, keybindingAction);
    }

    public static void putKeyBindingOnPressAndRelease(JComponent container, int inputMap, int key, AbstractAction onPressAction, String onPressDesc, AbstractAction onReleaseAction, String onReleaseDesc) {
        putKeyBinding(container, inputMap, key, false, onPressAction, onPressDesc);
        putKeyBinding(container, inputMap, key, true, onReleaseAction, onReleaseDesc);
    }
}
