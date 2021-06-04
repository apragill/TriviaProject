import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TriviaGUI extends JFrame{
    
    JPanel pnltop,pnlcenter,pnlbottom,pnlmostbottom,pnllast,pnlquestiontype;
    
    JLabel lblheading,lbltimer,lblquestion,lblcorrect,lblwrong;
    
    JTextField txtanswer;
    
    JButton btnnext,btnexit, btnadd,btnsubtract, btnmultiply;
    
    Timer t = new Timer();
    
    char op='+';
    
    int correct=1, wrong=1,qcount=1, second=10, result = 0;
    
    Question question;
    public TriviaGUI(){
        
        // call build method, which create components of GUI
        build();
        // labelheading panel
        add(pnltop);
        add(pnlquestiontype);
        add(pnlcenter);
        add(pnlbottom);
        add(pnlmostbottom);
        add(pnllast);
        
        question = new Question(); // Question class object
        problem(); // load first problem
        start();  // start timer
    }
    
    //build method to create components
    public void build(){
        
    pnltop = new JPanel();
    pnltop.setBackground(Color.blue); 
    
    pnlquestiontype = new JPanel();
    pnlquestiontype.setBackground(Color.blue); 
    
    pnlcenter = new JPanel();
    pnlcenter.setBackground(Color.blue);
    
    pnlbottom = new JPanel();
    pnlbottom.setBackground(Color.blue);
    
    pnlmostbottom = new JPanel();
    pnlmostbottom.setBackground(Color.blue);
    
    pnllast = new JPanel();
    pnllast.setBackground(Color.blue);
    
    lblheading = new JLabel("Trivia Game Questions");
    lblheading.setFont(new Font("Arial Bold",Font.BOLD,40));
    lblheading.setForeground(Color.white);
    pnltop.add(lblheading);
    
    
    btnadd = new JButton("+");
    btnadd.setBackground(Color.BLUE);
    //btnadd.setForeground(Color.white);
    btnadd.setFont(new Font("Arial Bold",Font.BOLD,30));
    pnlquestiontype.add(btnadd);
    
    btnsubtract = new JButton("-");
    btnsubtract.setBackground(Color.BLUE);
    //btnsubtract.setForeground(Color.white);
    btnsubtract.setFont(new Font("Arial Bold",Font.BOLD,30));
    pnlquestiontype.add(btnsubtract);
    
    btnmultiply = new JButton("*");
    btnmultiply.setBackground(Color.BLUE);
    //btnmultiply.setForeground(Color.white);
    btnmultiply.setFont(new Font("Arial Bold",Font.BOLD,30));
    pnlquestiontype.add(btnmultiply);
    
    lbltimer = new JLabel("Remaining time: 10 Seconds");
    lbltimer.setFont(new Font("Arial Bold",Font.BOLD,20));
    lbltimer.setForeground(Color.white);
    pnlcenter.add(lbltimer);
    
    
    lblquestion = new JLabel("Q#1:         2+2");
    lblquestion.setFont(new Font("Arial Bold",Font.BOLD,16));
    lblquestion.setForeground(Color.white);
    pnlbottom.add(lblquestion);
    
    txtanswer = new JTextField("",10);
    txtanswer.setFont(new Font("Arial Bold",Font.BOLD,12));
    pnlbottom.add(txtanswer);
    
    btnnext = new JButton("Next");
    btnnext.setBackground(Color.BLUE);
    //btnnext.setForeground(Color.white);
    btnnext.setFont(new Font("Arial Bold",Font.BOLD,12));
    pnlmostbottom.add(btnnext);
    
    btnexit = new JButton("Exit");
    btnexit.setBackground(Color.BLUE);
   // btnexit.setForeground(Color.white);
    btnexit.setFont(new Font("Arial Bold",Font.BOLD,12));
    pnlmostbottom.add(btnexit);
    
    lblcorrect = new JLabel("Correct: 0");
    lblcorrect.setFont(new Font("Arial Bold",Font.PLAIN,16));
    lblcorrect.setForeground(Color.green);
   // lblcorrect.setBorder(border);
    pnllast.add(lblcorrect);
    
    lblwrong = new JLabel("Wrong: 0");
    lblwrong.setFont(new Font("Arial Bold",Font.PLAIN,16));
    lblwrong.setForeground(Color.red);
    pnllast.add(lblwrong);
    
    btnnext.addActionListener(new ButtonHandler());
    btnexit.addActionListener(new ButtonHandler());
    btnadd.addActionListener(new ButtonHandler());
    btnsubtract.addActionListener(new ButtonHandler());
    btnmultiply.addActionListener(new ButtonHandler());
    }
    
    // check the result, if correct, increment correct, else increment wrong
    public void check(){
        try{
         result = Integer.parseInt(txtanswer.getText());
        }catch(NumberFormatException e){
            result = 0;
        }
        boolean correctanswer = question.checkAnswer(result);
        
        if(correctanswer)
            lblcorrect.setText("Correct: "+correct++);
        else
            lblwrong.setText("Wrong: "+wrong++);
        
        txtanswer.setText("");
        problem();
    }
    
    // exit program
    public void exit(){
        System.exit(0);
    }
    
    // load problem 
    public void problem(){
        String q;
        q = question.getProblem(op);
        lblquestion.setText("Q#"+qcount+":  "+q);
        System.out.println(q);
        
        second = 10; // restart timer
    }
    
    // set timer counter
    //set Time Counter
 TimerTask task = new TimerTask(){
     @Override
     public void run() {
         second--;
         lbltimer.setText("Remaining Time: "+second+" seconds");
         if(second == 0){
             check();
         }
         
         if(second <= 5 && second > 3)
             lbltimer.setForeground(Color.yellow);
         else if(second <= 3 && second >= 0)
             lbltimer.setForeground(Color.green);
         else 
             lbltimer.setForeground(Color.white);
     }
     
 };
    // start timer
    public void start(){
     t.scheduleAtFixedRate(task, 1000, 1000);
  }
    
    // button handler
    private class ButtonHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           switch(ae.getActionCommand()){
               case "Next":
                       check();
                       break;
               case "Exit":
                   exit();
                   break;
               case "+":
                   op = '+';
                   problem();
                   break;
               case "-":
                   op = '-';
                   problem();
                   break;
               case "*":
                   System.out.println(ae.getActionCommand());
                   op='*';
                   problem();
                   break;
           }
        }
        
    }
}

    
