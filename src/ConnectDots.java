import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ConnectDots extends JFrame implements ActionListener {

    private CanvasPanel canvasPanel = new CanvasPanel();
    private JButton drawBTN = new JButton("Draw");

    private boolean draw = false;

    public ConnectDots(){
        this.setTitle("Connect Dots");
        this.setSize(400, 400);
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.PAGE_AXIS));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void init(){
        drawBTN.addActionListener(this);
        this.add(canvasPanel);
        this.add(drawBTN);
        this.setVisible(true);

    }

    class CanvasPanel extends JFrame implements MouseListener {

        private ArrayList<Point> points = new ArrayList<>();

        public CanvasPanel() {
            addMouseListener(this);
            this.setBackground(Color.GRAY);
        }

        public void paintComponents(Graphics g) {
            super.paintComponents(g);

            if(draw) {
                for(int i = 0; i < points.size() - 1; i++) {
                    g.drawLine(points.get(i).x, points.get(i).y,points.get(i+1).x, points.get(i+1).y);
                }

                g.drawLine(points.get(0).x, points.get(0).y, points.get(points.size()-1).x, points.get(points.size()-1).y);
            }

            for(Point p : points) {
                g.setColor(Color.RED);
                g.fillOval(p.x -5, p.y - 5, 10,10);
            }
        }

        public void drawCalled()
        {
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("mouse clicked");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            points.add(new Point(e.getX(), e.getY()));

            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    class Point{
        int x, y;

        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        draw = true;
        canvasPanel.drawCalled();
    }

    public static void main(String[] args) {
        new ConnectDots().init();
    }


}
