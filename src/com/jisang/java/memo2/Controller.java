package com.jisang.java.memo2;

import java.util.ArrayList;

public class Controller {

	View view;
	Model model;
	Memo memo;
	int index;

	public static boolean Run = true;

	public Controller() {/* 메인에서 생성하니까 바로 여기서 초기화 해줌. */
		view = new View();
		model = new Model();
	}

	public void run() {

		while (Run) {

			String std = view.intro();

			switch (std) {
			case "c":
				memo = view.create();
				memo.date = System.currentTimeMillis(); // 따로 입력이 필요가 없으므로.
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
