package com.jisang.java.memo2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Model {

	ArrayList<Memo> data = new ArrayList<>();

	private final String DB_DIR = "C:\\Users\\Jisang\\workspace\\JAVA\\files2";
	private final String DB_NAME = "memo.txt";
	private final String SEP = "::";
	File tempMemo = new File(DB_DIR + "\\" + DB_NAME);

	public void addMemo(Memo memo) {

		File dir = new File(DB_DIR);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		if (!tempMemo.exists()) {
			try {
				tempMemo.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String oneLine = memo.no + SEP + memo.title + SEP + memo.content + SEP + memo.date + "\n";

		try {
			FileOutputStream fis = new FileOutputStream(tempMemo, true); // true¸¦
																			// ÇÏ°í,
																			// ¾Æ¿ôÇ²
			OutputStreamWriter os = new OutputStreamWriter(fis, "UTF-8");
			BufferedWriter bw = new BufferedWriter(os); // append¸¦ ÇÏ¸é °ªÀÌ ´Þ¶óºÙÀ½.
			bw.append(oneLine);
			bw.flush();
			bw.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

		memo.no = data.size() + 1;
		data.add(memo);
	}

	public Memo getMemo(int index) {

		data.clear();
		try {
			FileInputStream fis = new FileInputStream(tempMemo); // ÀÎÇ²
			InputStreamReader is = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(is);

			String temp = "";
			while ((temp = br.readLine()) != null) {
				String[] splitTemp = temp.split(SEP);
				Memo memo= new Memo();
				memo.no = Integer.parseInt(splitTemp[0]);
				memo.title = splitTemp[1];
				memo.content = splitTemp[2];
				memo.date = Long.parseLong(splitTemp[3]);
				data.add(memo);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		System.out.println(data.size());

		Memo temp = null;
		for (Memo memo : data) {
			if (memo.no == index) {
				temp = memo;
				return temp;
			}
		}
		return null;
	}

	public void update(Memo memo) {
		data.set(memo.no - 1, memo); // ±×³É µ¤¾î¾º¿ò
	}

	public void delete(int index) {
		data.remove(index - 1);
	}

}
