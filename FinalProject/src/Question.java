
import java.util.Random;

public class Question {

 int correct;
 
 public String getProblem(char op){
    int firstvalue, secondvalue;
    
     Random rand = new Random();
     
     firstvalue =rand.nextInt(10)+1;
     secondvalue = rand.nextInt(10)+1;
     
     if(op == '+')
         correct = firstvalue + secondvalue;
     else if(op == '-'){
         if(firstvalue >= secondvalue)
             correct = firstvalue - secondvalue;
         else{
             int temp=0;
             temp = firstvalue;
             firstvalue = secondvalue;
             secondvalue = temp;
             correct = firstvalue - secondvalue;
         }
     }
     else if(op == '*')
         correct = firstvalue * secondvalue;
     System.out.println(op);
     return firstvalue+" "+String.valueOf(op)+" "+secondvalue;
  }
 
 public boolean checkAnswer(int result){
     if(result == correct)
         return true;
     else 
         return false;
 }
 
 
}
