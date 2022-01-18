package ������;
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
		
		
		String h [] = {"��ID", "�̸�", "��ȭ��ȣ", "��������", "������ȣ"};
		for(int i = 0; i < 10; i++) {
			cell = row.createCell(i);
			cell.setCellValue(i);
		}
		
//		String s[] = {"MSY", "WHY", "JAY", "CWB", "CMJ", "KSY", "A10", "A20", "A30", "A40"};
//		ArrayList<String> list = new ArrayList<String>();
//		list.add(s[i]); //for������ ������ �ֱ�, �ؽ���, ���� ��� ����
		//2~10
		for(int r = 1; r<=19; r++) {
			row = sheet.createRow(r);
			String s[] = {"MSY", "WHY", "JAY", "CWB", "CMJ", "KSY", "A10", "A20", "A30", "A40"};
			for(int i = 0; i<10; i++) {
				cell = row.createCell(i);
				cell.setCellValue(s[i]);
				//cell.setCellValue(list.get(i));
			}
			
			// ���ͷ� �ص� ����
			//�ϳ��� ���� ��� ���� �̷���� �ֱ� ������ ���� for ��
		}
		
		//�Էµ� ���� ���Ϸ� ����
		File file = new File("./"+ fileName);
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(file);
			workbook.write(fos);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally { // ��ũ���� ���� �ƴϸ� Ŭ���� ó��
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
