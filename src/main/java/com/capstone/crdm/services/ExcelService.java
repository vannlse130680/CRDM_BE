package com.capstone.crdm.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Service
public class ExcelService {

    public static void main(String[] args) throws IOException {
        log.info("Writing an excel file...");
        var workbook = new XSSFWorkbook();

        ExcelService.createOutlookStructure(workbook);

        var batchSheet = workbook.getSheet("Batchsheet");
        var qtsxSheet = workbook.getSheet("QTSX");
        var bomSheet = workbook.getSheet("BOM");

        CellStyle normalStyle = workbook.createCellStyle();
        normalStyle.setBorderTop(BorderStyle.THIN);
        normalStyle.setBorderBottom(BorderStyle.THIN);
        normalStyle.setBorderLeft(BorderStyle.THIN);
        normalStyle.setBorderRight(BorderStyle.THIN);
        normalStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        CellStyle boldStyle = workbook.createCellStyle();
        boldStyle.setBorderTop(BorderStyle.THIN);
        boldStyle.setBorderBottom(BorderStyle.THIN);
        boldStyle.setBorderLeft(BorderStyle.THIN);
        boldStyle.setBorderRight(BorderStyle.THIN);
        boldStyle.setFont(ExcelService.getBoldFont(workbook));
        boldStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // first row
        Row firstRow = batchSheet.createRow(1);
        firstRow.setHeightInPoints(27);

        Cell productLabel = firstRow.createCell(0);
        productLabel.setCellValue("Sản phẩm:");
        productLabel.setCellStyle(normalStyle);
        batchSheet.addMergedRegion(CellRangeAddress.valueOf("C2:E2"));

        Cell formulaVersionLabel = firstRow.createCell(5);
        formulaVersionLabel.setCellValue("Formula Version");
        formulaVersionLabel.setCellStyle(boldStyle);
        batchSheet.addMergedRegion(CellRangeAddress.valueOf("G2:I2"));

        Cell lotLabel = firstRow.createCell(9);
        lotLabel.setCellValue("Số LOT sản xuất");
        lotLabel.setCellStyle(normalStyle);

        // second row
        Row secondRow = batchSheet.createRow(2);
        secondRow.setHeightInPoints(27);
        Cell brandLabel = secondRow.createCell(0);
        brandLabel.setCellValue("Thương hiệu:");
        brandLabel.setCellStyle(normalStyle);

        Cell definedRoundWeightLabel = secondRow.createCell(5);
        definedRoundWeightLabel.setCellValue("Khối lượng mẻ quy định (g):");
        definedRoundWeightLabel.setCellStyle(normalStyle);
        batchSheet.addMergedRegion(CellRangeAddress.valueOf("G3:I3"));

        Cell manufacturedDate = secondRow.createCell(9);
        manufacturedDate.setCellValue("Ngày sản xuất:");
        manufacturedDate.setCellStyle(normalStyle);

        // third row
        Row thirdRow = batchSheet.createRow(3);
        thirdRow.setHeightInPoints(27);
        Cell orderCodeLabel = thirdRow.createCell(0);
        orderCodeLabel.setCellValue("Mã đơn hàng:");

        Cell definedRoundLabel = thirdRow.createCell(5);
        definedRoundLabel.setCellValue("Số mẻ:");
        definedRoundLabel.setCellStyle(boldStyle);

        // fourth row
        Row fourthRow = batchSheet.createRow(4);
        fourthRow.setHeightInPoints(27);
        Cell capacityLabel = fourthRow.createCell(0);
        capacityLabel.setCellValue("Dung tích/Khối lượng đơn vị (ml/g)");

        Cell totalWeightLabel = fourthRow.createCell(5);
        totalWeightLabel.setCellValue("Tổng khối lượng:");

        Cell packagedDateLabel = fourthRow.createCell(9);
        packagedDateLabel.setCellValue("Ngày đóng gói:");

        // fifth row
        Row fifthRow = batchSheet.createRow(5);
        fifthRow.setHeightInPoints(27);
        Cell unitCostLabel = fifthRow.createCell(0);
        unitCostLabel.setCellValue("Hao hụt định mức:");

        Cell bsLabel = fifthRow.createCell(5);
        bsLabel.setCellValue("Số BS:");
        bsLabel.setCellStyle(boldStyle);

        // sixth row
        Row sixthRow = batchSheet.createRow(6);
        sixthRow.setHeightInPoints(27);
        Cell dLabel = sixthRow.createCell(0);
        dLabel.setCellValue("d (g/ml) ");


        // write to file
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
        log.info("Excel file written to disk.");
    }

    private static Font getBoldFont(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setBold(true);
        return font;
    }

    private static void createOutlookStructure(Workbook workbook) {
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

        var font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);


        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("PHIẾU THEO DÕI SẢN XUẤT");
        headerCell.setCellStyle(headerStyle);
    }

}
