package com.haohang.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

import com.haohang.ration.Ration;

public class QuizUtils {
	
	private static HashSet<Character> num_set = new HashSet<>();
	
	static {
		num_set.add('0');
		num_set.add('1');
		num_set.add('2');
		num_set.add('3');
		num_set.add('4');
		num_set.add('5');
		num_set.add('6');
		num_set.add('7');
		num_set.add('8');
		num_set.add('9');
	}
	
	private QuizUtils() {}
	
	public static void main(String[] args) {
		generateQuiz(0, 10);
//		String str = "3+1/4/3"; //123*+45*-6-78*+9- and 
//		String expr = toRPN(str);
//		String result = caclRPN(expr);
//		System.out.println(result);
	}
	
	public static void generateQuiz(int range, int num) {
		
	}
	
	public static String caclRPN(String expr) {
		Stack<Ration> ration_stack = new Stack<>();
		char[] chars = expr.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (num_set.contains(c)) {
				int num = Integer.parseInt(String.valueOf(c));
				ration_stack.push(new Ration(num, 1));
			} else {
				Ration r2 = ration_stack.pop();
				Ration r1 = ration_stack.pop();
				if (c == '+') {
					ration_stack.push(r1.add(r2));
				}
				if (c == '-') {
					ration_stack.push(r1.sub(r2));
				}
				if (c == '*') {
					ration_stack.push(r1.mul(r2));
				}
				if (c == '/') {
					ration_stack.push(r1.div(r2));
				}
			}
		}
		return ration_stack.pop().toString();
	}

    public static String toRPN(String str) {
    		Stack<Character> operators = new Stack<>(); 
    		StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        int pre = 0;
        boolean digital; 
        int len = chars.length;
        int bracket = 0;

        for (int i = 0; i < len; ) {
            pre = i;
            digital = Boolean.FALSE;
            while (i < len && !Operator.isOperator(chars[i])) {
                i++;
                digital = Boolean.TRUE;
            }

            if (digital) {
                sb.append(str.substring(pre, i));
            } else {
                char o = chars[i++]; 
                if (o == '(') {
                    bracket++;
                }
                if (bracket > 0) {
                    if (o == ')') {
                        while (!operators.empty()) {
                            char top = operators.pop();
                            if (top == '(') {
                                break;
                            }
                            sb.append(top);
                        }
                        bracket--;
                    } else {
                        while (!operators.empty() && operators.peek() != '(' && Operator.cmp(o, operators.peek()) <= 0) {
                            sb.append(operators.pop());
                        }
                        operators.push(o);
                    }
                } else {
                    while (!operators.empty() && Operator.cmp(o, operators.peek()) <= 0) {
                        sb.append(operators.pop());
                    }
                    operators.push(o);
                }
            }

        }
        while (!operators.empty()) {
            sb.append(operators.pop());
        }
        return new String(sb);
    }
}

enum Operator {
    ADD('+', 1), SUBTRACT('-', 1),
    MULTIPLY('*', 2), DIVIDE('/', 2),
    LEFT_BRACKET('(', 3), RIGHT_BRACKET(')', 3);
    char value;
    int priority;

    Operator(char value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    public static int cmp(char c1, char c2) {
        int p1 = 0;
        int p2 = 0;
        for (Operator o : Operator.values()) {
            if (o.value == c1) {
                p1 = o.priority;
            }
            if (o.value == c2) {
                p2 = o.priority;
            }
        }
        return p1 - p2;
    }

    public static boolean isOperator(char c) {
        for (Operator o : Operator.values()) {
            if (o.value == c) {
                return true;
            }
        }
        return false;
    }
	
}
