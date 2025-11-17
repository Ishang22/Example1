package Practice1;

import java.util.NoSuchElementException;
import java.util.Stack;

public class MinStack {

    private final Stack<Integer> stack;

    private final Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }


    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }


    public void pop() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        int popped = stack.pop();

        if (!minStack.isEmpty() && popped == minStack.peek()) {
            minStack.pop();
        }
    }


    public int top() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return stack.peek();
    }


    public int getMin() {
        if (minStack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return minStack.peek();
    }


    public static void main(String[] args) {
        MinStack ms = new MinStack();
        ms.push(5);
        ms.push(2);
        ms.push(2);
        ms.push(3);

        System.out.println("Top: " + ms.top());
        System.out.println("Min: " + ms.getMin());

        ms.pop();
        System.out.println("Top: " + ms.top());
        System.out.println("Min: " + ms.getMin());

        ms.pop();
        System.out.println("Top: " + ms.top());
        System.out.println("Min: " + ms.getMin());

        ms.pop();
        System.out.println("Top: " + ms.top());
        System.out.println("Min: " + ms.getMin());
    }
}
