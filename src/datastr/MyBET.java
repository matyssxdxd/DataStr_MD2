package datastr;

import java.util.Stack;

public class MyBET {

    private MyStackNode top = null;

    private void push(MyBETreeNode node) {
        if (top == null) top = new MyStackNode(node);
        else {
            MyStackNode temp = new MyStackNode(node);
            temp.next = top;
            top = temp;
        }
    }

    private MyBETreeNode pop() throws Exception {
        if (top == null) throw new Exception("There is nothing to pop");
        else {
            MyBETreeNode node = top.treeNode;
            top = top.next;
            return node;
        }
    }

    private MyBETreeNode peek() {
        return top.treeNode;
    }

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private int toDigit(char ch) {
        return ch - '0';
    }

    private void insert(char val) {
        try {
            if (isDigit(val)) {
                MyBETreeNode temp = new MyBETreeNode(val);
                push(temp);
            } else if (isOperator(val)) {
                MyBETreeNode temp = new MyBETreeNode(val);
                temp.left = pop();
                temp.right = pop();
                push(temp);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public void build(String equation) {
        equation = infixToPrefix(equation.toCharArray());
        for (int i = equation.length() - 1; i >= 0; i--) {
            insert(equation.charAt(i));
        }
    }

    public double evaluate(MyBETreeNode node) {
        if (node.left == null && node.right == null) return toDigit(node.element);
        else {
            double result = 0.0;
            double left = evaluate(node.left);
            double right = evaluate(node.right);
            char operator = node.element;

            result = switch (operator) {
                case '+' -> left + right;
                case '-' -> left - right;
                case '*' -> left * right;
                case '/' -> left / right;
                default -> left + right;
            };

            return result;
        }
    }

    public double evaluate() {
        return evaluate(peek());
    }

    static String reverse(char str[], int start, int end)
    {
        // Temporary variable to store character
        char temp;
        while (start < end) {
            // Swapping the first and last character
            temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end--;
        }
        return String.valueOf(str);
    }

    static boolean isalpha(char c)
    {
        if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
            return true;
        }
        return false;
    }

    // Function to check if the character is digit
    static boolean isdigit(char c)
    {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    // Function to check if the character is an operator
    static boolean isOperator(char c)
    {
        return (!isalpha(c) && !isdigit(c));
    }

    static int getPriority(char C)
    {
        if (C == '-' || C == '+')
            return 1;
        else if (C == '*' || C == '/')
            return 2;
        else if (C == '^')
            return 3;

        return 0;
    }

    static String infixToPostfix(char[] infix1)
    {
        String infix = '(' + String.valueOf(infix1) + ')';

        int l = infix.length();
        Stack<Character> char_stack = new Stack<>();
        String output = "";

        for (int i = 0; i < l; i++) {

            // If the scanned character is an
            // operand, add it to output.
            if (isalpha(infix.charAt(i))
                    || isdigit(infix.charAt(i)))
                output += infix.charAt(i);

                // If the scanned character is an
                // ‘(‘, push it to the stack.
            else if (infix.charAt(i) == '(')
                char_stack.add('(');

                // If the scanned character is an
                // ‘)’, pop and output from the stack
                // until an ‘(‘ is encountered.
            else if (infix.charAt(i) == ')') {
                while (char_stack.peek() != '(') {
                    output += char_stack.peek();
                    char_stack.pop();
                }

                // Remove '(' from the stack
                char_stack.pop();
            }

            // Operator found
            else {
                if (isOperator(char_stack.peek())) {
                    while (
                            (getPriority(infix.charAt(i))
                                    < getPriority(char_stack.peek()))
                                    || (getPriority(infix.charAt(i))
                                    <= getPriority(
                                    char_stack.peek())
                                    && infix.charAt(i) == '^')) {
                        output += char_stack.peek();
                        char_stack.pop();
                    }

                    // Push current Operator on stack
                    char_stack.add(infix.charAt(i));
                }
            }
        }
        while (!char_stack.empty()) {
            output += char_stack.pop();
        }
        return output;
    }

    static String infixToPrefix(char[] infix)
    {
        // Reverse String and replace ( with ) and vice
        // versa Get Postfix Reverse Postfix
        int l = infix.length;

        // Reverse infix
        String infix1 = reverse(infix, 0, l - 1);
        infix = infix1.toCharArray();

        // Replace ( with ) and vice versa
        for (int i = 0; i < l; i++) {

            if (infix[i] == '(') {
                infix[i] = ')';
                i++;
            }
            else if (infix[i] == ')') {
                infix[i] = '(';
                i++;
            }
        }

        String prefix = infixToPostfix(infix);

        // Reverse postfix
        prefix = reverse(prefix.toCharArray(), 0, l - 1);

        return prefix;
    }

}
