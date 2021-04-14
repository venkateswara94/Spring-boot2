package com.hw.service;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.poi.hpsf.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hw.entity.ArticleEntity;
import com.hw.repository.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	public List<ArticleEntity> findArticles() {
		return articleRepository.findAll();
	}

	public ByteArrayInputStream articesToExcel() throws IOException {
		String[] COLUMNs = { "Aid", "Title", "Author", "Batch_year" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper createHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("ARTCLES_LIST");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			// CellStyle for Age
			CellStyle ageCellStyle = workbook.createCellStyle();
			ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

			int rowIdx = 1;
			for (ArticleEntity article : findArticles()) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(article.getAid());
				row.createCell(1).setCellValue(article.getA_title());
				row.createCell(2).setCellValue(article.getA_author());
				Cell yearCell = row.createCell(3);
				yearCell.setCellValue(new Date().toString());
				yearCell.setCellStyle(ageCellStyle);
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public void articesToTextFile(String filePath)throws Exception {
		try {
			FileOutputStream outputStream = new FileOutputStream(filePath);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
			BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
			List<ArticleEntity> list = findArticles();
			bufferedWriter.write("ArticleId  |  ArticleTitle  |  AuthorName");
			bufferedWriter.newLine();
			list.forEach(artcl->{
				try {
					bufferedWriter.write(artcl.getAid() +" | "+artcl.getA_title()+" | "+artcl.getA_author());
					bufferedWriter.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
