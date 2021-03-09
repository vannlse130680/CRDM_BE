package com.capstone.crdm.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Service
public class ExcelService {

    @PostConstruct
    public void printExcelFile() throws IOException {
        log.info("Writing an excel file...");
        var workbook = new XSSFWorkbook();

        var batchSheet = workbook.createSheet("Batchsheet");
        var qtsxSheet = workbook.createSheet("QTSX");
        var bomSheet = workbook.createSheet("BOM");

        // set column width
        batchSheet.setColumnWidth(0, 1250);
        batchSheet.setColumnWidth(1, 2000);
        batchSheet.setColumnWidth(2, 7004);
        batchSheet.setColumnWidth(3, 2965);
        batchSheet.setColumnWidth(4, 2519);
        batchSheet.setColumnWidth(5, 5632);
        batchSheet.setColumnWidth(6, 2594);
        batchSheet.setColumnWidth(7, 2410);
        batchSheet.setColumnWidth(8, 2410);
        batchSheet.setColumnWidth(9, 2298);
        batchSheet.setColumnWidth(10, 2519);
        batchSheet.setColumnWidth(11, 7152);
        batchSheet.setColumnWidth(12, 2112);
        batchSheet.setColumnWidth(13, 2187);
        batchSheet.setColumnWidth(14, 2187);
        batchSheet.setColumnWidth(15, 2187);

        // create header
        batchSheet.addMergedRegion(CellRangeAddress.valueOf("A1:L1"));

        var header = batchSheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();

        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);


        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("PHIẾU THEO DÕI SẢN XUẤT");
        headerCell.setCellStyle(headerStyle);

        CellStyle normalStyle = workbook.createCellStyle();

        // first row
        Row firstRow = batchSheet.createRow(1);

        Cell productLabel = firstRow.createCell(0);
        productLabel.setCellValue("Sản phẩm:");
        productLabel.setCellStyle(normalStyle);
        batchSheet.addMergedRegion(CellRangeAddress.valueOf("C2:E2"));

        Cell formulaVersionLabel = firstRow.createCell(5);
        formulaVersionLabel.setCellValue("Formula Version");
        formulaVersionLabel.setCellStyle(normalStyle);
        batchSheet.addMergedRegion(CellRangeAddress.valueOf("G2:I2"));

        Cell lotLabel = firstRow.createCell(9);
        lotLabel.setCellValue("Số LOT sản xuất");
        lotLabel.setCellStyle(normalStyle);

        // second row
        Row secondRow = batchSheet.createRow(2);
        Cell brandLabel = secondRow.createCell(0);
        brandLabel.setCellValue("Thương hiệu:");
        brandLabel.setCellStyle(normalStyle);

        Cell definedRoundWeightLabel = secondRow.createCell(5);
        definedRoundWeightLabel.setCellValue("Khối lượng mẻ quy định (g):");
        definedRoundWeightLabel.setCellStyle(normalStyle);
        batchSheet.addMergedRegion(CellRangeAddress.valueOf("G3:I3"));

        Cell manufacturedDate = secondRow.createCell(9);
        manufacturedDate.setCellValue("'Ngày sản xuất:");
        manufacturedDate.setCellStyle(normalStyle);

        // write to file
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
        log.info("Excel file written to disk.");
    }
}
