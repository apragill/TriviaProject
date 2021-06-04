
import java.awt.GridLayout;
import javax.swing.JFrame;

public class TriviaGameApp {    
 public static void main(String args[]){
     //TriviaGUI frame = new TriviaGUI();
     TriviaGUI frame = new TriviaGUI();
     frame.setSize(500,500);
     frame.setLayout(new GridLayout(6,1));
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setVisible(true);
 }    
}

