package hw6collections;

import java.util.Stack;
import java.util.EmptyStackException; 
class StackExample
{
 static void showpush(Stack<Object> st, int a)
 { 
  st.push(new Integer(a));
  System.out.println("push(" + a + ")");
  System.out.println("stack: " + st);
 }
 static void showpop(Stack<Object> st)
 { 
  System.out.print("pop -> ");
  Integer a = (Integer) st.pop();
  System.out.println(a);
  System.out.println("stack: " + st);
 }
 public static void main(String args[])
 { 
  Stack<Object> st = new Stack<>();
  System.out.println("stack: " + st);
  showpush(st, 42);
  showpush(st, 66);
  showpush(st, 99);
  showpop(st);
  showpop(st);
  showpop(st);
  try
  {
   showpop(st);
  } 
  catch (EmptyStackException e)
  { 
   System.out.println("empty stack");
  }
 }
}
