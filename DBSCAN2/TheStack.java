// Mohamed Oussama El Malki - 300248698
import java.util.Stack;
 
public class TheStack<E> {
    private Stack<E> stack;
 
    // Constructor to create empty Stack.
    public TheStack() { 
        stack = new Stack<E>(); }
 
    // check if stack is empty
    public boolean empty() {
         return stack.empty(); }

    //push an element into the stack
    public E push(E item) {
        return stack.push(item); }


    //remove and return the element at the topstack
    public E pop() { 
        return stack.pop(); }
}