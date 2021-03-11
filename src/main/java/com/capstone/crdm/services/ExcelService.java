package com.capstone.crdm.services;

import com.capstone.crdm.entities.FormulaEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

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
        ExcelService.processSheetBatch(workbook);

        // write to file
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
        log.info("Excel file written to disk.");
    }

    private static Font getBoldFont(XSSFWorkbook workbook, int fontSize, boolean bold) {
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


    private static void processSheetBatch(XSSFWorkbook workbook) {
        var batchSheet = workbook.getSheet("Batchsheet");
        var qtsxSheet = workbook.getSheet("QTSX");
        var bomSheet = workbook.getSheet("BOM");

        // define styles
        XSSFCellStyle normalStyle = workbook.createCellStyle();
        normalStyle.setBorderTop(BorderStyle.THIN);
        normalStyle.setBorderBottom(BorderStyle.THIN);
        normalStyle.setBorderLeft(BorderStyle.THIN);
        normalStyle.setBorderRight(BorderStyle.THIN);
        normalStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFCellStyle boldStyle = workbook.createCellStyle();
        boldStyle.setBorderTop(BorderStyle.THIN);
        boldStyle.setBorderBottom(BorderStyle.THIN);
        boldStyle.setBorderLeft(BorderStyle.THIN);
        boldStyle.setBorderRight(BorderStyle.THIN);
        boldStyle.setFont(ExcelService.getBoldFont(workbook, 11, true));
        boldStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFCellStyle boldStyleWrapped = workbook.createCellStyle();
        boldStyleWrapped.setBorderTop(BorderStyle.THIN);
        boldStyleWrapped.setBorderBottom(BorderStyle.THIN);
        boldStyleWrapped.setBorderLeft(BorderStyle.THIN);
        boldStyleWrapped.setBorderRight(BorderStyle.THIN);
        boldStyleWrapped.setFont(ExcelService.getBoldFont(workbook, 11, true));
        boldStyleWrapped.setVerticalAlignment(VerticalAlignment.CENTER);
        boldStyleWrapped.setAlignment(HorizontalAlignment.CENTER);
        boldStyleWrapped.setWrapText(true);

        XSSFCellStyle lowerBordered = workbook.createCellStyle();
        lowerBordered.setFont(ExcelService.getBoldFont(workbook, 11, true));
        lowerBordered.setBorderBottom(BorderStyle.THIN);
        lowerBordered.setBorderLeft(BorderStyle.THIN);
        lowerBordered.setBorderRight(BorderStyle.THIN);

        XSSFCellStyle rightBordered = workbook.createCellStyle();
        rightBordered.setFont(ExcelService.getBoldFont(workbook, 11, true));
        rightBordered.setBorderBottom(BorderStyle.THIN);
        rightBordered.setBorderTop(BorderStyle.THIN);
        rightBordered.setBorderRight(BorderStyle.THIN);

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
        Cell qualityLabel = fifthRow.createCell(0);
        qualityLabel.setCellValue("Số lượng");

        Cell expirationDate = fifthRow.createCell(9);
        expirationDate.setCellValue("Hạn sử dụng:");
        batchSheet.addMergedRegion(CellRangeAddress.valueOf("J6:K6"));

        // fifth row
        Row sixthRow = batchSheet.createRow(6);
        sixthRow.setHeightInPoints(27);
        Cell unitCostLabel = sixthRow.createCell(0);
        unitCostLabel.setCellValue("Hao hụt định mức:");

        Cell bsLabel = sixthRow.createCell(5);
        bsLabel.setCellValue("Số BS:");
        bsLabel.setCellStyle(boldStyle);

        // sixth row
        Row seventhRow = batchSheet.createRow(7);
        seventhRow.setHeightInPoints(27);
        Cell dLabel = seventhRow.createCell(0);
        dLabel.setCellValue("d (g/ml) ");

        // draw ingredients table
        Row eleventhRow = batchSheet.createRow(10);
        Row twelfthRow = batchSheet.createRow(11);
        batchSheet.addMergedRegion(CellRangeAddress.valueOf("A11:A12"));
        batchSheet.addMergedRegion(CellRangeAddress.valueOf("B11:B12"));
        batchSheet.addMergedRegion(CellRangeAddress.valueOf("D11:D12"));
        batchSheet.addMergedRegion(CellRangeAddress.valueOf("E11:F11"));
        batchSheet.addMergedRegion(CellRangeAddress.valueOf("G11:G12"));

        // Number
        Cell numberColumnHeader = eleventhRow.createCell(0);
        numberColumnHeader.setCellValue("STT");
        numberColumnHeader.setCellStyle(boldStyle.copy());

        Cell lowerNumberColumnHeader = twelfthRow.createCell(0);
        lowerNumberColumnHeader.setCellStyle(lowerBordered.copy());

        // Phase
        Cell phaseColumnHeader = eleventhRow.createCell(1);
        phaseColumnHeader.setCellValue("Pha");
        phaseColumnHeader.setCellStyle(boldStyle);

        Cell lowerPhaseColumnHeader = twelfthRow.createCell(1);
        lowerPhaseColumnHeader.setCellStyle(lowerBordered.copy());

        // Ingredients
        Cell ingredientHeader = eleventhRow.createCell(2);
        ingredientHeader.setCellValue("Nguyên liệu");
        ingredientHeader.setCellStyle(boldStyle);

        Cell commercialNameHeader = twelfthRow.createCell(2);
        commercialNameHeader.setCellValue("Tên thương mại");
        commercialNameHeader.setCellStyle(boldStyle);

        // Ratio
        Cell ratioHeader = eleventhRow.createCell(3);
        ratioHeader.setCellValue("Tỉ lệ\n (%w/w)");
        ratioHeader.setCellStyle(boldStyleWrapped);

        Cell lowerRatioHeaderr = twelfthRow.createCell(3);
        lowerRatioHeaderr.setCellStyle(lowerBordered.copy());

        // klcqd
        Cell klcqdHeader = eleventhRow.createCell(4);
        klcqdHeader.setCellValue("Khối lượng cân quy định");
        klcqdHeader.setCellStyle(boldStyleWrapped);

        Cell rightKlcqdHeader = eleventhRow.createCell(5);
        rightKlcqdHeader.setCellStyle(rightBordered);

        Cell gHeader = twelfthRow.createCell(4);
        gHeader.setCellValue("g");
        gHeader.setCellStyle(boldStyleWrapped);

        Cell toleranceHeader = twelfthRow.createCell(5);
        toleranceHeader.setCellValue("Dung sai");
        toleranceHeader.setCellStyle(boldStyleWrapped);

        // measured value
        Cell trueValueHeader = eleventhRow.createCell(6);
        trueValueHeader.setCellValue("Thực tế");
        trueValueHeader.setCellStyle(boldStyleWrapped);

        Cell lowerTrueValueHeader = twelfthRow.createCell(6);
        lowerTrueValueHeader.setCellStyle(lowerBordered.copy());
    }

    private static void printIngredientsTable(XSSFWorkbook workbook, FormulaEntity formula) {

    }

}
