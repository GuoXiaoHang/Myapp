package com.haohang.main;

import com.haohang.GUI.MyAppGUI;
import com.haohang.utils.QuizUtils;

public class Main {
	
	public static void main(String[] args) {
		int range = -1,num = -1;
		boolean r_exist = false;
		for (int i = 0; i < args.length; i+=2) {
			switch (args[i]) {
			case "-r":
				if (Integer.parseInt(args[i+1]) > 0) {
					r_exist = true;
					range = Integer.parseInt(args[i+1]);					
				}
				break;
			case "-n":
				num = Integer.parseInt(args[i+1]);
				break;

			default:
				System.out.println("Can't recognize arguments!");
				break;
			}
		}
		if (!r_exist) {
			System.out.println("You must enter -r parameter! or -r must be a positive number");
			System.exit(0);
		} else {
			QuizUtils.generateQuiz(range, num);
		}
		new MyAppGUI();
	}

}
