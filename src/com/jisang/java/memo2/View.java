package com.jisang.java.memo2;

import java.util.Scanner;

public class View {

	Scanner scanner = new Scanner(System.in);

	public String intro() {

		println("----------��ɾ �Է��ϼ���------------");
		println("c.����, r.�б�, u.���� , d.����  l.���  x.����");
		println("����------------------------------");
		return scanner.nextLine();
	}

	public Memo create() {
		Memo memo = new Memo();

		print("Title>");
		memo.title = scanner.nextLine();

		print("Content>");
		memo.content = scanner.nextLine();

		memo.date = System.currentTimeMillis();

		return memo;

	}

	public int readMemo() {
		print("�ε����� �Է��ϼ���>");
		int index = Integer.parseInt(scanner.nextLine());
		return index;
	}
	
	public void showMemo(Memo memo){
		print("|NO :"+ memo.no);
		print("|title :"+ memo.title);
		print("|content :"+ memo.content);
		print("|date :"+ memo.date);
		
	}
	
	public Memo updateMemo(){
		
		Memo memo = new Memo();
		print("�ε����� �Է��ϼ���>");
		memo.no = Integer.parseInt(scanner.nextLine());
		print("Title : ");
		memo.title = scanner.nextLine();
		print("Content : ");
		memo.content =  scanner.nextLine();
		
		return memo;
	}
	
	public int deleteMemo(){
		print("�ε����� �Է��ϼ���.");
		int index = Integer.parseInt(scanner.nextLine());
		return index;
		
	}
	

	public void println(String str) {
		System.out.println(str);
	}

	public void print(String str) {
		System.out.println(str);
	}
}
