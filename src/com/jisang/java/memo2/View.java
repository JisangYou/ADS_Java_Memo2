package com.jisang.java.memo2;

import java.util.Scanner;

public class View {

	Scanner scanner = new Scanner(System.in);

	public String intro() {

		println("----------명령어를 입력하세요------------");
		println("c.쓰기, r.읽기, u.수정 , d.삭제  l.목록  x.종료");
		println("선택------------------------------");
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
		print("인덱스를 입력하세요>");
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
		print("인덱스를 입력하세요>");
		memo.no = Integer.parseInt(scanner.nextLine());
		print("Title : ");
		memo.title = scanner.nextLine();
		print("Content : ");
		memo.content =  scanner.nextLine();
		
		return memo;
	}
	
	public int deleteMemo(){
		print("인덱스를 입력하세요.");
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
