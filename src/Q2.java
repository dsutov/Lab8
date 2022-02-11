import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Q2 extends JFrame implements ActionListener {

    private CanvasPanel canvas = new CanvasPanel();

    public Q2()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400,400);
    }

    public void init()
    {
        this.add(canvas);
        this.setVisible(true);
    }

    class CanvasPanel extends JPanel implements MouseListener, MouseMotionListener
    {
        Shape s = null;

        private Color [] colors = {Color.BLUE, Color.RED, Color.GREEN};
        private int colorTracker = 0;

        public CanvasPanel()
        {
            s = getRandomShape(0,0);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }

        private Shape getRandomShape(int xPos, int yPos)
        {
            int random = (int)(Math.random()*3);
            System.out.println(random);
            if(random == 0) {
                return new Rectangle2D.Double(xPos - 25, yPos - 25, 50, 50);
            }
            else if (random == 1)
            {
                return new Rectangle2D.Double(xPos - 50, yPos - 25, 100, 50);
            }
            else
                return new Ellipse2D.Double(xPos - 25, yPos - 25, 50, 50);
        }

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(colors[colorTracker]);

            g2d.fill(s);
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if(s.contains(mouseEvent.getPoint())) {
                colorTracker = (colorTracker + 1) % colors.length;
                repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            s = getRandomShape(mouseEvent.getX(), mouseEvent.getY());
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {

        }
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
    public static void main(String[] args) {
        new Q2().init();
    }


}