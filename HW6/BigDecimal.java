import java.util.*;
import java.io.*;

public class BigDecimal{
  
  private List<Character> decimal = new ArrayList<Character>();
  private boolean NaN;
  
  
  public BigDecimal(String input){
    NaN = false;
    int startIndex;
    if(input.equals("NaN")){
      NaN = true;
      return;
    }
    for(int i = 0; i < input.length(); i++){
      decimal.add(new Character(input.charAt(i))); 
    }
  }
  
  
  char add(char d1, char d2, char carry){
    int i1 = (int) d1 - 48;
    int i2 = (int) d2 - 48;
    int result = i1 + i2 + (int) carry - 48;
    if(result >= 10){
      result -= 10;
    }
    //System.out.println(result);
    return (char) (result + 48);
  }
  
  
  BigDecimal plus(BigDecimal n){
    String result = "", num1_ones, num2_ones, num1_dec, num2_dec;
    char carry = '0', out;
    if(NaN || n.toString().equals("NaN")){
      return new BigDecimal("NaN");
    }
    num1_dec = this.toString().substring(this.toString().indexOf('.') + 1, this.toString().length());
    num2_dec = n.toString().substring(n.toString().indexOf('.') + 1, n.toString().length());
    num1_ones = this.toString().substring(0, this.toString().indexOf('.'));
    num2_ones = n.toString().substring(0, n.toString().indexOf('.'));
    while(num1_ones.length() < num2_ones.length()){
      num1_ones = '0' + num1_ones;
    }
    while(num1_ones.length() > num2_ones.length()){
      num2_ones = '0' + num2_ones;
    }
    while(num1_dec.length() < num2_dec.length()){
      num1_dec = num1_dec + '0';
    }
    while(num1_dec.length() > num2_dec.length()){
      num2_dec = num2_dec + '0';
    }
    
    for(int i = num1_dec.length() - 1; i >= 0; i--){
      out = add(num1_dec.charAt(i), num2_dec.charAt(i), carry);
      result = out + result;
      if(out >= num1_dec.charAt(i) && out >= num2_dec.charAt(i) && out >= carry)){
        carry = '0';
      }else{
        carry = '1';
      }
    }
    result = '.' + result;
    for(int i = num1_ones.length() - 1; i >= 0; i--){
      out = add(num1_ones.charAt(i), num2_ones.charAt(i), carry);
      result = out + result;
      if(out >= num1_ones.charAt(i) && out >= num2_ones.charAt(i) && out >= carry)){
        carry = '0';
      }else{
        carry = '1';
      }
    }
    if(carry == '1'){
      result = '1' + result;
    }
    while(result.charAt(result.length() - 1) == '0'){
      result = result.substring(0, result.length() - 1);
    }
    return new BigDecimal(result);
  }
  
  
  public String toString(){
    String out = "";
    if(NaN == true){
      return "NaN";
    }
    for(int i = 0; i < decimal.size(); i++){
      out = out + decimal.get(i);
    }
    return out;
  }
  
  BigDecimal div(BigDecimal n){
    if(n.toString().equals("'1")){
      return this;
    }
    if(n.toString().equals("NaN") || n.toString().equals("0")){
      return new BigDecimal("NaN");
    }
    System.out.println("Invalid denominator entered.");
    return new BigDecimal("NaN");
  }
  
  public static void main(String[] args){
    BigDecimal big = new BigDecimal("20.545");
    BigDecimal big2 = new BigDecimal("91.655");
    System.out.println(big.plus(big2));
  }
  
  
}
