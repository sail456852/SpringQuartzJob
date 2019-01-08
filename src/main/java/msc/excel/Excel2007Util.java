package msc.excel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Excel2007Util<T> {

	public static String NO_DEFINE = "no_define";// 未定义的字段
	public static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";// 默认日期格式
	public static int DEFAULT_COLOUMN_WIDTH = 17;// 最小的列宽值

	/**
	 * 使用指定的参数创建SXSSFWorkbook对象
	 * 
	 * @param cacheSize
	 *            excel操作记录的缓存大小
	 * @param compress
	 *            是否对文件进行压缩，true是，false否
	 * @return SXSSFWorkbook
	 */
	public SXSSFWorkbook createWorkbook(int cacheSize, boolean compress) {
		// 设置excel缓存的行数
		SXSSFWorkbook workbook = new SXSSFWorkbook(5000);
		workbook.setCompressTempFiles(true);
		return workbook;
	}

	/**
	 * 创建标题单元格的样式对象
	 * 
	 * @param workbook
	 * @param height
	 *            单元格的高度
	 * @param weight
	 *            单元格的宽度
	 * @return
	 */
	private CellStyle createTitleCellStyle(SXSSFWorkbook workbook,
			Short height, Short weight) {
		height = height == null ? 20 : height;
		weight = weight == null ? 800 : weight;
		CellStyle titleStyle = workbook.createCellStyle();
//		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		Font titleFont = workbook.createFont();
//		titleFont.setFontHeightInPoints(height);
//		titleFont.setBoldweight(weight);
//		titleStyle.setFont(titleFont);
		return titleStyle;
	}

	/**
	 * 创建列名称单元格的样式对象
	 * 
	 * @param workbook
	 * @return
	 */
	private CellStyle createHeaderCellStyle(SXSSFWorkbook workbook) {
		CellStyle headerStyle = workbook.createCellStyle();
//		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		Font headerFont = workbook.createFont();
//		headerFont.setFontHeightInPoints((short) 12);
//		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		headerStyle.setFont(headerFont); @author: eugene @date: 2019/1/8
		return headerStyle;
	}

	/**
	 * 创建内容单元格的样式对象
	 * 
	 * @param workbook
	 * @return
	 */
	@SuppressWarnings("unused")
	private static CellStyle createContentCellStyle(SXSSFWorkbook workbook) {
		CellStyle cellStyle = workbook.createCellStyle();
//		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//		Font cellFont = workbook.createFont();
//		cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//		cellStyle.setFont(cellFont);
		return cellStyle;
	}

	/**
	 * 创建表格的sheet工作单元
	 * 
	 * @param workbook
	 * @param title
	 *            如果不为空，则sheet第一行内容为此title
	 * @param columnWidth
	 *            列宽
	 * @param sheetTitleMap
	 *            内容标题集合，key为数据的属性名称，value为标题名称
	 * @return sheet
	 */
	private Sheet createSheet(SXSSFWorkbook workbook, String title,
			int columnWidth, Map<String, String> sheetTitleMap) {
		Sheet sheet = workbook.createSheet();

		// 设置列宽
		int minBytes = columnWidth < DEFAULT_COLOUMN_WIDTH ? DEFAULT_COLOUMN_WIDTH
				: columnWidth;
		int[] titleArray = new int[sheetTitleMap.size()];

		int firstRowIndex = 0;
		if (StringUtils.isNotBlank(title)) {
			// 创建表格第一行大标题
			Row titleRow = sheet.createRow(0);
			titleRow.createCell(0).setCellValue(title);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, sheetTitleMap
					.size() - 1));
			firstRowIndex = 1;
		}

		// 创建表格内容标题
		Row headerRow = sheet.createRow(firstRowIndex);
		int index = 0;
		String fieldName;
		for (Iterator<String> iter = sheetTitleMap.keySet().iterator(); iter
				.hasNext();) {
			fieldName = iter.next();

			int bytes = fieldName.getBytes().length;
			titleArray[index] = bytes < minBytes ? minBytes : bytes;
			sheet.setColumnWidth(index, titleArray[index] * 256);
			headerRow.createCell(index).setCellValue(
					sheetTitleMap.get(fieldName));
			index++;
		}
		return sheet;
	}
	
	/**
	 * 导出Excel 2007格式的表格
	 * 
	 * @param workbook
	 *            SXSSFWorkbook对象
	 * @param headMap
	 *            标题名称和内容属性名称的集合，key为表格内容的属性名称，value为标题名称
	 * @param dataList
	 *            需要导出的数据集合
	 * @param dataSize
	 *            导出数据的起始数，如同一次操作只导一次则为0，而同一次操作需要导多次，则后一次传前几次的数据总和。
	 *            如：第一次导10000，传0；第二次导20000，传10000；第三次导50000，传30000
	 * @return SXSSFWorkbook
	 */
	public SXSSFWorkbook export2007Excel(SXSSFWorkbook workbook,
			Map<String, String> headMap, List<T> dataList, int dataSize) {
		return export2007Excel(workbook, null, headMap, dataList, null, 0, 200000, dataSize);
	}

	/**
	 * 导出Excel 2007格式的表格
	 * 
	 * @param workbook
	 *            SXSSFWorkbook对象
	 * @param title
	 *            sheet工作单元第一行标题，如果为空则不生成此行
	 * @param headMap
	 *            标题名称和内容属性名称的集合，key为表格内容的属性名称，value为标题名称
	 * @param dataList
	 *            需要导出的数据集合
	 * @param datePattern
	 *            日期字段的格式化样式，默认yyyy-MM-dd HH:mm:ss
	 * @param colWidth
	 *            列宽，默认最小17
	 * @param maxRows
	 *            每个sheet单元最大行数
	 * @param dataSize
	 *            导出数据的起始数，如同一次操作只导一次则为0，而同一次操作需要导多次，则后一次传前几次的数据总和。
	 *            如：第一次导10000，传0；第二次导20000，传10000；第三次导50000，传30000
	 * @return SXSSFWorkbook
	 */
	public SXSSFWorkbook export2007Excel(SXSSFWorkbook workbook,
			String title, Map<String, String> headMap, List<T> dataList,
			String datePattern, int colWidth, int maxRows, int dataSize) {
		JSONArray jsonArray = listConvertJSONArray(dataList);
		if (datePattern == null) {
			datePattern = DEFAULT_DATE_PATTERN;
		}

		String[] properties = new String[headMap.size()];
		int index = 0;
		for (Iterator<String> iter = headMap.keySet().iterator(); iter
				.hasNext();) {
			properties[index++] = iter.next();
		}

		// 遍历集合数据，产生数据行
		Sheet sheet = null;
		int rowIndex = dataSize;
		int totalIndex = dataSize;
//		CellStyle contentCellStyle = createContentCellStyle(workbook);
		for (Object obj : jsonArray) {
			if (totalIndex % maxRows == 0) {
				sheet = createSheet(workbook, title, colWidth, headMap);
				if (StringUtils.isNotBlank(title)) {
					rowIndex = 2;
					sheet.getRow(0).getCell(0).setCellStyle(createTitleCellStyle(workbook, null, null));
					Row headRow = sheet.getRow(1);
					CellStyle cellStyle = createHeaderCellStyle(workbook);
					Iterator<Cell> iterator = headRow.cellIterator();
					while (iterator.hasNext()) {
						Cell cell = iterator.next();
						cell.setCellStyle(cellStyle);
					}
				} else {
					rowIndex = 1;
					sheet.getRow(0)
							.setRowStyle(createHeaderCellStyle(workbook));
				}
			} else {
				sheet = workbook.getSheetAt(totalIndex / maxRows);
				rowIndex = sheet.getLastRowNum() + 1;
			}

			JSONObject jo = (JSONObject) JSONObject.toJSON(obj);
			Row dataRow = sheet.createRow(rowIndex);
			for (int i = 0; i < properties.length; i++) {
				Cell newCell = dataRow.createCell(i);
				Object o = jo.get(properties[i]);
				String cellValue = "";
				if (o == null)
					cellValue = "";
				else if (o instanceof Date)
					cellValue = new SimpleDateFormat(datePattern).format(o);
				else if (o instanceof Float || o instanceof Double)
					cellValue = new BigDecimal(o.toString()).setScale(2,
							BigDecimal.ROUND_HALF_UP).toString();
				else
					cellValue = o.toString();

				newCell.setCellValue(cellValue);
//				newCell.setCellStyle(contentCellStyle);
			}
			totalIndex++;
		}
		return workbook;
	}
	
	private JSONArray listConvertJSONArray(List<T> dataList){
		JSONArray jsonArray = new JSONArray();
		for (T object : dataList) {
			jsonArray.add(object);
		}
		return jsonArray;
	}
}
