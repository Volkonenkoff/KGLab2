import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;


public class Window extends JFrame {
    private JPanel contentPane;
    private JButton buttonRefresh;
    private JSlider rotationAngle;
    private JTextField aX;
    private JTextField aZ;
    private JTextField aY;
    private JTextField bX;
    private JTextField bZ;
    private JTextField bY;
    private JPanel display;
    private JPanel A;
    private JPanel B;
    private Cube CubeFigure;

    private Axis X;
    private Axis Y;
    private Axis Z;
    private Axis newAxis;
    public Window() {
        setContentPane(contentPane);
        this.setMinimumSize(new Dimension(1280,720));
        this.setMaximumSize(new Dimension(1280,720));
        this.setResizable(false);
        this.setTitle("КГ №1 Вариант 6");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        rotationAngle.setValue(0);
        rotationAngle.setToolTipText(String.valueOf(rotationAngle.getValue())+" °");
        rotationAngle.addChangeListener(e->{
            rotationAngle.setToolTipText(String.valueOf(rotationAngle.getValue())+" °");
        });
        newAxis= new Axis(new Dot (0,0,0),new Dot(50,0,50));
        aX.setText(String.valueOf(newAxis.A.x));
        aY.setText(String.valueOf(newAxis.A.y));
        aZ.setText(String.valueOf(newAxis.A.z));
        bX.setText(String.valueOf(newAxis.B.x));
        bY.setText(String.valueOf(newAxis.B.y));
        bZ.setText(String.valueOf(newAxis.B.z));
        X=new Axis(new Dot(0,0,0),
                new Dot(1,0,0));

        Y=new Axis(new Dot(0,0,0),
                new Dot(0,1,0));
        Z=new Axis(new Dot(0,0,0),
                new Dot(0,0,1));

        X.rotation();
        X.projection();
        Y.rotation();
        Y.projection();
        Z.rotation();
        Z.projection();
        newAxis.rotation();
        newAxis.projection();
        CubeFigure=new Cube();
        for (int i=0;i<CubeFigure.shapesArray.size();i++) {
            Shapes temp = (Shapes) CubeFigure.shapesArray.get(i);
            temp.rotation();
            temp.projection();
        }
        JPanel Paint = new JPanel()
        {
          public void paintComponent(Graphics drawing)
          {
              Graphics2D drawing2D = (Graphics2D) drawing;
              drawing2D.setColor(Color.BLACK);
              drawing2D.fillRect(0, 0, 720, 720);
              drawing2D.translate(360, 360);
              drawing2D.setColor(Color.DARK_GRAY);

              Path2D path = new Path2D.Double();
              Path2D axisPath = new Path2D.Double();
              Path2D newAxisPath = new Path2D.Double();

              axisPath.moveTo(X.A.x,X.A.y);
              axisPath.lineTo(X.B.x*500,X.B.y*500);
              axisPath.lineTo(-X.B.x*500,-X.B.y*500);
              axisPath.moveTo(Y.A.x,Y.B.y);
              axisPath.lineTo(Y.B.x*500,Y.B.y*500);
              axisPath.lineTo(-Y.B.x*500,-Y.B.y*500);
              axisPath.moveTo(Z.A.x,Z.B.y);
              axisPath.lineTo(Z.B.x*500,Z.B.y*500);
              axisPath.lineTo(-Z.B.x*500,-Z.B.y*500);
              axisPath.moveTo(0,0);
              axisPath.closePath();
              drawing2D.draw(axisPath);
              drawing2D.setColor(Color.CYAN);
              newAxisPath.moveTo(newAxis.A.x,newAxis.A.y);
              newAxisPath.lineTo(newAxis.B.x,newAxis.B.y);
              newAxisPath.closePath();
              drawing2D.draw(newAxisPath);
              drawing2D.setColor(Color.RED);
              for (int i=0;i<CubeFigure.shapesArray.size();i++) {
                  Shapes temp=(Shapes)CubeFigure.shapesArray.get(i);


                  path.moveTo(temp.d1.x*50
                          , temp.d1.y*50);

                  path.lineTo(temp.d2.x*50
                          , temp.d2.y*50);

                  path.lineTo(temp.d3.x*50
                          , temp.d3.y*50);

                  path.lineTo(temp.d4.x*50
                          , temp.d4.y*50);

                  path.closePath();
                  drawing2D.draw(path);
              }
              path.closePath();

              drawing2D.draw(path);
          }

        };

        buttonRefresh.addActionListener(e->{
            try{
                if (aX.getText().equals("") || aY.getText().equals("") ||
                aZ.getText().equals("") || bX.getText().equals("") ||
                        bY.getText().equals("") || bZ.getText().equals("")
                )
                    throw new NullPointerException();
                newAxis.A.x=Double.parseDouble(aX.getText());
                newAxis.A.y=Double.parseDouble(aY.getText());
                newAxis.A.z=Double.parseDouble(aZ.getText());
                newAxis.B.x=Double.parseDouble(bX.getText());
                newAxis.B.y=Double.parseDouble(bY.getText());
                newAxis.B.z=Double.parseDouble(bZ.getText());
                newAxis.rotation();
                newAxis.projection();
                Paint.repaint();
            } catch(NullPointerException ex)
            {
                JOptionPane.showMessageDialog(this,"Пусто поле","Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        display.add(Paint);

    }


    public static void main(String[] args) {
        Window dialog = new Window();
        dialog.pack();
        dialog.setVisible(true);

    }


}
