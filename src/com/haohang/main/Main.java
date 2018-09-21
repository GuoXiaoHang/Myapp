package com.haohang.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;

import com.haohang.GUI.MyAppGUI;
import com.haohang.ration.Ration;
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
				if (range > 10) {
					System.out.println("-r can't greater than 10");
					System.exit(0);
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
			HashSet<String> set = QuizUtils.generateQuiz(range, num);
			File file = new File("./Exercises.txt");
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String excercise_line = null;
			int i = 1;
			for (String string : set) {
//				System.out.println(string);
				excercise_line = new String(i + ". " + string);
				i++;
				try {
					bw.write(excercise_line);
					bw.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				String str = QuizUtils.caclRPN(QuizUtils.toRPN(string));
//				System.out.println(Ration.toDaiFenShu(str));
			}
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("hello");
		}
	}

}
