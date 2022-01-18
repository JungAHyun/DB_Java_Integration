package 정아현;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelhanderler {

	public static void main(String[] args) {
		String fileName = "custdata.xlsx";
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();	
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell;
		
		
		String h [] = {"고객ID", "이름", "전화번호", "차량종류", "차량번호"};
		for(int i = 0; i < 10; i++) {
			cell = row.createCell(i);
			cell.setCellValue(i);
		}
		
//		String s[] = {"MSY", "WHY", "JAY", "CWB", "CMJ", "KSY", "A10", "A20", "A30", "A40"};
//		ArrayList<String> list = new ArrayList<String>();
//		list.add(s[i]); //for문으로 데이터 넣기, 해쉬맵, 벡터 모두 가능
		//2~10
		for(int r = 1; r<=19; r++) {
			row = sheet.createRow(r);
			String s[] = {"MSY", "WHY", "JAY", "CWB", "CMJ", "KSY", "A10", "A20", "A30", "A40"};
			for(int i = 0; i<10; i++) {
				cell = row.createCell(i);
				cell.setCellValue(s[i]);
				//cell.setCellValue(list.get(i));
			}
			
			// 벡터로 해도 무관
			//하나의 셀은 행과 열로 이루어져 있기 때문에 이중 for 문
		}
		
		//입력된 내용 파일로 쓰기
		File file = new File("./"+ fileName);
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(file);
			workbook.write(fos);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally { // 워크북이 널이 아니면 클로즈 처리
			try {
				if(workbook!=null) workbook.close();
				if(fos!=null) fos.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

}
