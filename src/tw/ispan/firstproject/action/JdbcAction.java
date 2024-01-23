package tw.ispan.firstproject.action;

import java.util.Scanner;

import tw.ispan.firstproject.entity.Medicine;
import tw.ispan.firstproject.service.IMedicineDao;
import tw.ispan.firstproject.service.MedicineDaoFactory;

public class JdbcAction {

	public static void main(String[] args) throws Exception {
		IMedicineDao mDao = MedicineDaoFactory.createMedicineDao();
		mDao.createConn();
		int count = 0;
		while (count < 10) {
			System.out.println(
					"請輸入欲執行題目號:" + '\r' + '\n' + "1.根據主鍵取資料" + '\r' + '\n' + "2.根據關鍵字刪除資料" + '\r' + '\n' + "3.根據主鍵更新資料"
							+ '\r' + '\n' + "4.將圖片存入SQL Server" + '\r' + '\n' + "5.根據圖片ID賭取資料並寫入磁碟中" + '\r' + '\n');
			Scanner scanner = new Scanner(System.in);
			int num = scanner.nextInt();
			switch (num) {
			case 1:

				System.out.print("請輸入欲查詢ID:");
				int keyinId = scanner.nextInt();
				Medicine m1 = mDao.getDataById(keyinId);
				System.out.println("ID: " + m1.getId() + '\r' + '\n' + "日期: " + m1.getDate() + '\r' + '\n' + "藥名: "
						+ m1.getName() + '\r' + '\n' + "許可證號: " + m1.getLicenseCode() + '\r' + '\n' + "供應狀態: "
						+ m1.getSupplyStatus());
				System.out.println("---------------------------------------------");
				break;

			case 2:

				System.out.print("請輸入欲刪除藥名:");
				Scanner deleteName = new Scanner(System.in);
				String name = deleteName.nextLine();
				mDao.deleteByName(name);
				System.out.println("---------------------------------------------");
				break;

			case 3:

				System.out.println("根據主鍵更新資料,請輸入欲更正藥品名的ID:");
				int updateId = scanner.nextInt();
				if (updateId > 0 && updateId <= 384) {
					System.out.println("根據主鍵更新資料,請輸入欲更正的Name:");
					Scanner sName = new Scanner(System.in);
					String updateName = sName.nextLine();
					Medicine m3 = new Medicine(updateId, updateName);
					mDao.updateById(m3);
				} else {
					System.out.println("無此ID");
				}
				System.out.println("---------------------------------------------");
				break;

			case 4:

				System.out.println("請輸入欲存入圖片檔名:");
				Scanner s = new Scanner(System.in);
				String picName = s.nextLine();
				mDao.savePic(picName);
				System.out.println("---------------------------------------------");
				break;

			case 5:

				System.out.println("請輸入欲讀取圖片ID:");
				int id = scanner.nextInt();
				System.out.println("請輸入欲儲存檔名");
				Scanner sName = new Scanner(System.in);
				name = sName.nextLine();
				mDao.getPicById(id, name);
				System.out.println("---------------------------------------------");
				break;
				
			default:
				System.out.println("題目輸入錯誤");
				System.out.println("---------------------------------------------");
					
			}
			count++;
		}
		mDao.closeConn();

	}

}
