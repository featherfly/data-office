//package cn.featherfly.data.office.word;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.List;
//
//import javax.servlet.http.HttpServlet;
//
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFPictureData;
//import org.apache.poi.xwpf.usermodel.XWPFTable;
//import org.apache.poi.xwpf.usermodel.XWPFTableCell;
//import org.apache.poi.xwpf.usermodel.XWPFTableRow;
//
//
//
//public class Word extends HttpServlet {
//
//	private static final long serialVersionUID = 1L;
//
//	public XWPFDocument document;
//
//	/**
//	 */
//	public Word(XWPFDocument document) {
//		this.document = document;
//	}
//
//	public Word(InputStream is) throws IOException {
//		this(new XWPFDocument(is));
//	}
//
//	public Word(File file) throws IOException {
//		this(new XWPFDocument(new FileInputStream(file)));
//	}
//
//	private String getString(int tableIndex, int rowIndex, int cellIndex) throws IOException {
//		XWPFTableRow row = document.getTables().get(tableIndex).getRow(rowIndex);
//		return row.getCell(cellIndex).getText();
//	}
//
//	public String getOrganName() throws IOException {
//		return getString(0, 0, 1);
//	}
//
//	public void readDoc() throws IOException {
//		InputStream in = this.getClass().getResourceAsStream(this.getClass().getSimpleName() + ".docx");
//		XWPFDocument document = new XWPFDocument(in);
//		List<XWPFTable> list = document.getTables();
//		int tabNum = 0;
//		for (XWPFTable table : list) {
//			int rowNum = 0;
//			for (XWPFTableRow row : table.getRows()) {
//				int cellNum = 0;
//				for (XWPFTableCell cell : row.getTableCells()) {
//					System.out.println("tabNum: " + tabNum + "\trow: " + rowNum + "\tcell: " + cellNum
//							+ "\t" + cell.getText());
//					cellNum++;
//				}
//				rowNum++;
//			}
//			tabNum++;
//		}
//	}
//
//	public static void main(String[] args) throws IOException, InvalidFormatException {
//		InputStream in = Word.class.getResourceAsStream(Word.class.getSimpleName() + ".docx");
//		XWPFDocument document = new XWPFDocument(in);
//		Word word = new Word(document);
////		word.readDoc();
//		System.out.println("单位名称：" + word.getOrganName());
//		System.out.println("单位性质：" + word.getString(0, 1, 1));
//		System.out.println("法定代表人：" + word.getString(0, 1, 3));
//		System.out.println("单位人数：" + word.getString(0, 2, 0));
//		System.out.println("涉密人员数：" + word.getString(0, 2, 3));
//		System.out.println("注册地址：" + word.getString(0, 3, 1));
//		System.out.println("通信地址：" + word.getString(0, 4, 1));
//		System.out.println("邮政编码：" + word.getString(0, 5, 1));
//		System.out.println("联系电话：" + word.getString(0, 5, 3));
//		System.out.println("单位基本情况：" + word.getString(0, 6, 1));
//		in.close();
////		tabNum: 0	row: 0	cell: 0	单位名称
////		tabNum: 0	row: 0	cell: 1	______成都XXXX息技术有限公司___________
////		tabNum: 0	row: 1	cell: 0	单位性质
////		tabNum: 0	row: 1	cell: 1	军工
////		tabNum: 0	row: 1	cell: 2	法定代表人
////		tabNum: 0	row: 1	cell: 3	张三
////		tabNum: 0	row: 2	cell: 0	单位人数
////		tabNum: 0	row: 2	cell: 1	100
////		tabNum: 0	row: 2	cell: 2	涉密人员数
////		tabNum: 0	row: 2	cell: 3	12
////		tabNum: 0	row: 3	cell: 0	注册地址
////		tabNum: 0	row: 3	cell: 1	成都市武侯区
////		tabNum: 0	row: 4	cell: 0	通信地址
////		tabNum: 0	row: 4	cell: 1	成都市人民南路四段
////		tabNum: 0	row: 5	cell: 0	邮政编码
////		tabNum: 0	row: 5	cell: 1	610000
////		tabNum: 0	row: 5	cell: 2	联系电话
////		tabNum: 0	row: 5	cell: 3	85251487
////		tabNum: 0	row: 6	cell: 0	单位基本情况
////		tabNum: 0	row: 6	cell: 1	单位基本情况
//
//		in = Word.class.getResourceAsStream("pic.docx");
//		document = new XWPFDocument(in);
//		for (XWPFPictureData pd : document.getAllPictures()) {
//			System.out.println(pd.getFileName() + " - " + pd.getPictureType()
//					+ " - " +pd.getRelationId(document.getPart()));
//			OutputStream os = new FileOutputStream(pd.getFileName());
//			os.write(pd.getData());
//			os.flush();
//			os.close();
//		}
//		in.close();
////			document.write(new FileOutputStream(new Date().getTime()+".docx"));
//	}
//}
