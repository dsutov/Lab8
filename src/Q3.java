import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Q3 extends JFrame implements ActionListener {

    private CanvasPanel canvas = new CanvasPanel();
    private JButton rectangleBTN = new JButton("Rectangle");
    private JButton circleBTN = new JButton("Circle");
    private JButton squareBTN = new JButton("Square");

    private JButton changeColorBTN = new JButton("Change Color");
    private JButton changeShapeBTN = new JButton("Change Shape");
    private JLabel shapeLabel = new JLabel("Shape: "), shapeSelect = new JLabel();
    private JLabel colourLabel = new JLabel("Colour: "), colourSelect = new JLabel();

    private String shapeList[] = {"Rectangle", "Square", "Circle"};
    private int colorTracker = 0;


    public Q3()
    {
        this.setTitle("Q3");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void init()
    {
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
        //drawPane.add(rectangleBTN);
        //drawPane.add(circleBTN);
        //drawPane.add(squareBTN);




        canvas.add(drawPane, BorderLayout.NORTH);

        rectangleBTN.addActionListener(this);
        circleBTN.addActionListener(this);
        squareBTN.addActionListener(this);
        changeColorBTN.addActionListener(this);
        changeShapeBTN.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        colorTracker = (colorTracker + 1) % shapeList.length;
        if(e.getSource() == rectangleBTN){
            System.out.println("Rectangle Pressed");
            shapeSelect.setText("Rectangle");
        } else if(e.getSource() == circleBTN){
            System.out.println("Circle Pressed");
            shapeSelect.setText("Circle");
        } else if(e.getSource() == squareBTN){
            System.out.println("Square Pressed");
            shapeSelect.setText("Square");
        } else if(e.getSource() == changeColorBTN){
            System.out.println("Color Pressed");
            colourSelect.setText("Test");
        } else if(e.getSource() == changeShapeBTN){
            System.out.println("Change shape pressed");
            getShape(colorTracker);
            System.out.println(colorTracker);
            shapeSelect.setText(shapeList[colorTracker]);
        }
    }

    private Shape getShape(int i)
    {

        System.out.println(i);
        if(i == 0) { // if random is 0 is draws a square
            return new Rectangle2D.Double(100, 100, 50, 50);
        }
        else if (i == 1) // if random is 1 is draws a rectangle
        {
            return new Rectangle2D.Double(100, 100, 100, 50);
        }
        else // if random is 3 is draws a circle
            return new Ellipse2D.Double(100, 100, 50, 50);
    }

    class CanvasPanel extends JPanel implements MouseListener, MouseMotionListener {
        private Rectangle2D rect = new Rectangle2D.Double(100,100,50,50);

        private boolean pressed = false;

        private Color[] colors = {Color.BLUE, Color.RED, Color.GREEN};

        private int colorTracker = 0;

        public CanvasPanel(){
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }



        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(colors[colorTracker]);

            g2d.fill(rect);

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(rect.contains(e.getPoint())) {
                colorTracker = (colorTracker + 1) % colors.length;
                repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(rect.contains(e.getPoint()))
            {
                //System.out.println("Square is pressed");
                pressed = true;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            pressed = false;
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
                double w = rect.getWidth();
                double h = rect.getHeight();
                double x = e.getX() - (w / 2);
                double y = e.getY() - (h / 2);

                rect = new Rectangle2D.Double(x, y, w, h);

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
}