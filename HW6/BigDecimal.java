import java.util.*;
import java.io.*;

public class BigDecimal{
    
    List<Character> decimal = new ArrayList<Character>();
    int decimalPlace;
    boolean NaN = true;
    boolean neg;
    
    public BigDecimal(String input){
        NaN = false;
        int startIndex;
        if(input.charAt(0) == '-'){
            neg = true;
            startIndex = 1;
        } else {
            neg = false;
            startIndex = 0;
        }
        for(int i = startIndex; i < input.length(); i++){
            decimal.add(new Character(input.charAt(i)));
            if(input.charAt(i) == '.'){
                decimalPlace = startIndex + i;
            }
            
        }
        
    }
    
    
    
}