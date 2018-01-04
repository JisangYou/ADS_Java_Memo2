package com.jisang.java.memo2;

import java.util.ArrayList;

public class Controller {

	View view;
	Model model;
	Memo memo;
	int index;

	public static boolean Run = true;

	public Controller() {/* ���ο��� �����ϴϱ� �ٷ� ���⼭ �ʱ�ȭ ����. */
		view = new View();
		model = new Model();
	}

	public void run() {

		while (Run) {

			String std = view.intro();

			switch (std) {
			case "c":
				memo = view.create();
				memo.date = System.currentTimeMillis(); // ���� �Է��� �ʿ䰡 �����Ƿ�.
				model.addMemo(memo);
				break;
			case "r":
				index = view.readMemo();
				memo = model.getMemo(index);
				view.showMemo(memo);
				break;
			case "u":
				Memo memo = view.updateMemo();
				model.update(memo);
				break;
			case "d":
				int index = view.deleteMemo();
				model.delete(index);

				break;
			case "l":
				ArrayList<Memo> data = model.getList();
				view.showList(data);
				break;
			case "x":
				Run = false;
				break;
			}
		}

	}

}
