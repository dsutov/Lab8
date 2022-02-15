import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Q3 extends JFrame implements ActionListener {

    private DrawingPanel canvas = new DrawingPanel();

    private JButton changeColorBTN = new JButton("Change Color");
    private JButton changeShapeBTN = new JButton("Change Shape");
    private JLabel shapeLabel = new JLabel("Shape: "), shapeSelect = new JLabel();
    private JLabel colourLabel = new JLabel("Colour: "), colourSelect = new JLabel();

    private String shapeList[] = {"Rectangle", "Square", "Circle"};
    private int shapeTracker = 0;
    private String colourList[] = {"Blue", "Red", "Green"};
    private int colorTracker = 0;

    public Q3(){
        this.setTitle("Q3 Test");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void init() {
        this.add(canvas);
        canvas.setBackground(Color.GRAY);
        JPanel drawPane = new JPanel();
        drawPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL; // fills the grid box horizontally
        gbc.insets = new Insets(1,1,1,1);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        drawPane.add(changeShapeBTN, gbc);

        gbc.gridx = 2;
        drawPane.add(changeColorBTN, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        drawPane.add(shapeLabel, gbc);

        gbc.gridx = 1;
        drawPane.add(shapeSelect, gbc);

        gbc.gridx = 2;
        drawPane.add(colourLabel, gbc);

        gbc.gridx = 3;
        drawPane.add(colourSelect, gbc);

        drawPane.setBackground(Color.WHITE);


        canvas.add(drawPane, BorderLayout.NORTH);

        changeColorBTN.addActionListener(this);
        changeShapeBTN.addActionListener(this);
    }

    class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {
        Shape s = null;

        private Color[] colors = {Color.BLUE, Color.RED, Color.GREEN};

        private boolean pressed = false;

        public DrawingPanel(){
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }

        private Shape getShape(int xPos, int yPos, int i)
        {
            System.out.println("Shape Called");
            if(i == 0) { // if random is 0 is draws a square
                return new Rectangle2D.Double(xPos - 25, yPos - 25, 100, 50);
            }
            else if (i == 1) // if random is 1 is draws a rectangle
            {
                return new Rectangle2D.Double(xPos - 50, yPos - 25, 50, 50);
            }
            else // if random is 3 is draws a circle
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
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(s.contains(e.getPoint()))
            {
                //System.out.println("Square is pressed");
                pressed = true;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            pressed = false;
            s = getShape(e.getX(), e.getY(), shapeTracker);
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(pressed) {
                double w = s.getBounds().getWidth();
                double h = s.getBounds().getHeight();
                double x = e.getX() - (w / 2);
                double y = e.getY() - (h / 2);

                s = new Rectangle2D.Double(x, y, w, h);

                repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    public static void main(String[] args) {
        new Q3().init();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == changeColorBTN){
            System.out.println("Color Pressed");
            colorTracker = (colorTracker + 1) % colourList.length;
            colourSelect.setText(colourList[colorTracker]);
        } else if(e.getSource() == changeShapeBTN){
            System.out.println("Change shape pressed");
            shapeTracker = (shapeTracker + 1) % shapeList.length;
            shapeSelect.setText(shapeList[shapeTracker]);
        }
    }
}