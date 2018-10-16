import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) throws ParseException {
		// dvd名称， 编号， 状态，借出时间，归还时间
		String[] names = new String[5]; // DVD名称
		String[] status = new String[5]; // 状态
		String[] times = new String[5]; // 借出时间
		Scanner in = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 定义默认的初始化数据
		names[0] = "遇见你真好";
		status[0] = "可借";
		times[0] = null;

		names[1] = "金刚葫芦娃";
		status[1] = "租赁中";
		times[1] = "2018-03-12";

		names[2] = "复仇者联盟";
		status[2] = "租赁中";
		times[2] = "2018-10-01";

		int an = 0;

		do {
			// 输出主菜单
			System.out.println("欢迎使用清风DVD出租系统");
			System.out.println("****************");
			System.out.println("1. 添加DVD");
			System.out.println("2. 出租DVD");
			System.out.println("3. 删除DVD"); // 替换为null
			System.out.println("4. 查询所有DVD");
			System.out.println("5. 归还DVD");
			System.out.println("6. 退出");
			System.out.println("****************");
			System.out.println("请选择：");
			int xuan = in.nextInt();

			switch (xuan) {
			case 1: // 添加DVD
				System.out.println("******添加DVD******");
				System.out.println("请输入新光盘的名称：");
				String name = in.next();
				for (int i = 0; i < names.length; i++) {
					if (names[i] == null) {
						names[i] = name;
						status[i] = "可借";
						times[i] = null;
						break;
					}
				}
				break;
			case 2: // 出租DVD
				System.out.println("******出租DVD******");
				for (int i = 0; i < names.length; i++) { // 用循环筛选出可出租的DVD
					if ("可借".equals(status[i])) {
						System.out.println((i + 1) + "\t" + names[i]);
					}
				}

				System.out.println("请输入您要租赁的光盘编号：");
				int num = in.nextInt(); // 当用户输入完光盘编号后把状态变为不可借
				status[num - 1] = "租赁中";

				Date d = new Date(); // 转换时间

				times[num - 1] = sdf.format(d);
				break;
			case 3: // 删除DVD
				System.out.println("******删除DVD******");
				boolean x = false;
				System.out.println("请输入您要下架的DVD名称：");
				String name1 = in.next(); // 用户输入要删除的DVD名称
				int id = -1;
				for (int i = 0; i < names.length; i++) {
					if (name1.equals(names[i]) && names[i] != null && "可借".equals(status[i])) {
						id = i;
						x = true;
						break;
					} else if (name1.equals(names[i]) && names[i] != null && "租赁中".equals(status[i])) {
						System.out.println("您选择的DVD正在租赁，无法下架！");
						x = true;
						break;
					}
				}
				// 未找到该序号订单：不能删除
				if (!x) {
					System.out.println("您要下架的DVD不存在！");
				}
				// 执行删除操作
				if (id != -1) {
					// 循环移位执行删除
					for (int i = id + 1; i < names.length - 1; i++) {
						names[i - 1] = names[i];
						status[i - 1] = status[i];
						times[i - 1] = times[i];
					}
					// 清空最后一位
					names[names.length - 1] = null;
					status[status.length - 1] = null;
					times[times.length - 1] = null;
					System.out.println("下架成功！");
				}
				break;
			case 4: // 查询所有DVD
				System.out.println("******查询所有DVD******");
				System.out.println("编号\t名称\t\t状态\t借出时间");
				for (int i = 0; i < names.length; i++) {
					if (names[i] == null) {
						break;
					}
					System.out.println( // 输出对应的参数，并用三目运算符输出借出时间
							(i + 1) + "\t" + names[i] + "\t" + status[i] + "\t"
									+ (times[i] == null ? "未借出" : times[i]));
				}
				break;
			case 5: // 归还DVD
				System.out.println("******归还DVD******");
				System.out.println("请输入要归还的DVD名称：");
				String num1 = in.next(); // 归还DVD的名称
				// 改状态，改时间， 算钱
				int money = 0;
				for (int i = 0; i < times.length; i++) {
					if (num1.equals(names[i])) {
						status[i] = "可借";
						Date d1 = sdf.parse(times[i]);
						Date d2 = new Date();
						int day = (int) (d2.getTime() - d1.getTime()) / 1000 / 60 / 60 / 24;
						money = day * 5;
						times[i] = null;
						break;
					}
				}
				// 算钱
				System.out.println("归还成功，交钱" + money);
				break;
			case 6: // 退出
				System.out.println("退出系统成功！欢迎下次光临！");
				return;
			}
			System.out.println("输入0返回");
			an = in.nextInt();
		} while (an == 0);
	}
}
