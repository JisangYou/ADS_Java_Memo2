# ADS04 Java 

## 보충수업 내용
- Memo1 프로젝트에서 못 따라간 부분 보충 학습
- 간단하게 핵심적인 부분 보충 학습

## Code Review

1. View

```Java
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
	
	public void showList(ArrayList<Memo> data){
		
		for(Memo memo : data){
		print("|NO :"+ memo.no);
		print("|title :"+ memo.title);
		print("|content :"+ memo.content);
		print("|date :"+ memo.date);
		}
	}
	

	public void println(String str) {
		System.out.println(str);
	}

	public void print(String str) {
		System.out.println(str);
	}
}
```

2. Model

```Java
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
		String oneLine = memo.no + SEP + memo.title + SEP + memo.content + SEP + memo.date + "\r\n";

		try {
			FileOutputStream fis = new FileOutputStream(tempMemo, true); // true를
																			// 하고,
																			// 아웃풋
			OutputStreamWriter os = new OutputStreamWriter(fis);
			BufferedWriter bw = new BufferedWriter(os); // append를 하면 값이 달라붙음.
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
		Memo memo = null;
		try {
			FileInputStream fis = new FileInputStream(tempMemo); // 인풋
			InputStreamReader is = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(is);
			
			String temp = "";
			while ((temp = br.readLine()) != null) {
				String[] splitTemp = temp.split(SEP);
				if(Integer.parseInt(splitTemp[0]) == index){
					memo = new Memo();
					memo.no = Integer.parseInt(splitTemp[0]);
					memo.title = splitTemp[1];
					memo.content = splitTemp[2];
					memo.date = Long.parseLong(splitTemp[3]);
				}
			}
			br.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println(data.size());
		return memo;
	}

	public void update(Memo memo) {
		data.set(memo.no - 1, memo); // 그냥 덮어씌움
	}

	public void delete(int index) {
		data.remove(index - 1);
	}

	public ArrayList<Memo> getList() {

		if(data != null)
		data.clear();
//		data = new ArrayList();
		try {
			FileInputStream fis = new FileInputStream(tempMemo); // 인풋
			InputStreamReader is = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(is);
			String temp = "";
			while ((temp = br.readLine()) != null) {
				Memo memo = new Memo();
				String[] splitTemp = temp.split(SEP);
				memo.no = Integer.parseInt(splitTemp[0]);
				memo.title = splitTemp[1];
				memo.content = splitTemp[2];
				memo.date = Long.parseLong(splitTemp[3]);
				data.add(memo);
//				System.out.println(memo.title);
			}
			br.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return data;
	}

}
```

3. Controller

```Java
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

```

4. Memo

```Java
public class Memo {/*model과 같은 역할*/

	int no;
	String title;
	String content;
	long date;
	
}
```

5. main

```Java
public class main {

	public static void main(String[] args) {
		
		Controller c = new Controller();
		c.run();

	}

}
```

## 보충설명

- 생성자란? 예를 들어 설명하면, 어떤 일을 시작하기 전에 준비를 하게 되는데 이것을 다른 말로 초기화라고 한다.
객체 지향 프로그래밍도 초기화에 해당하는 기능이 제공되는데 이것을 생성자(Constructor)라고 한다. 즉, 객첵를 생성할 때 항상 실행되며, 맨처음 실행되는 메소드이다.

- 예제코드
```Java
public Controller() {/* 메인에서 생성하니까 바로 여기서 초기화 해줌. */
		view = new View();
		model = new Model();
	}
```

- 특징
#### 반환값이 없는 메소드는 생성자가 유일하다. 
생성자는 인스턴스를 생성해주는 역할을 하는 특수한 메소드라고 할 수 있다. 반환 값이 없기 때문에 return도 사용하지 않고, 반환 값을 메소드 정의에 포함 시키지도 않는다.
#### 생성자의 이름은 클래스의 이름과 동일하다.
자바에서 클래스의 이름과 동일한 메소드는 생성자로 사용하기로 약속되어 있다.
#### 생성자는 매개변수에 따라서 여러 개를 만들 수 있다. 
이 중에 필요에 따라 객체를 생성시에 선택할 수 있다

#### 출처 - [생활코딩](https://opentutorials.org/module/2495/14065)

## TODO

- MVC패턴 연습 및 각 역할에 대한 고민 필요
- 클래스 및 메소드에 대한 깊은 이해 필요


## Retrospect

- 처음에 패턴이나 Class와 Method를 다루는 법이 미숙했는데, 보충학습을 통해 어느정도 이해가 되었음.
- 앞으로도 간단한 예제 및 큰 그림을 먼저 그린 후에 접근을 해야 스스로 생각하면서 문제를 해결할 수 있을 것이라고 생각이 됨.

## Output
- 생략