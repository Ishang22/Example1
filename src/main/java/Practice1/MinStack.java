package Practice1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;


public class MinStack {
    // main stack holds all values
    private final Deque<Integer> stack;
    // minStack holds the current minima (one or more entries)
    private final Deque<Integer> minStack;

    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    // push value onto stack; also push to minStack if it's new minimum
    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    // pop from stack; if popped value equals top of minStack, pop minStack too
    public void pop() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        int popped = stack.pop();
        // compare primitives to avoid Integer reference issues
        if (!minStack.isEmpty() && popped == minStack.peek()) {
            minStack.pop();
        }
    }

    // return top element
    public int top() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return stack.peek();
    }

    // return current minimum
    public int getMin() {
        if (minStack.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return minStack.peek();
    }

    // simple demo
    public static void main(String[] args) {
        MinStack ms = new MinStack();
        ms.push(5);
        ms.push(2);
        ms.push(2);
        ms.push(3);

        System.out.println("Top: " + ms.top());       // 3
        System.out.println("Min: " + ms.getMin());    // 2

        ms.pop(); // pop 3
        System.out.println("Top: " + ms.top());       // 2
        System.out.println("Min: " + ms.getMin());    // 2

        ms.pop(); // pop 2 (one of the 2s)
        System.out.println("Top: " + ms.top());       // 2
        System.out.println("Min: " + ms.getMin());    // 2

        ms.pop(); // pop last 2
        System.out.println("Top: " + ms.top());       // 5
        System.out.println("Min: " + ms.getMin());    // 5
    }
}

