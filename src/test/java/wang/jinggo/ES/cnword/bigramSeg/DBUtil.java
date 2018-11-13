package wang.jinggo.ES.cnword.bigramSeg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringTokenizer;

import junit.framework.TestCase;

import com.lietu.biSeg.DicDBFactory;

/**
 * 文本词典倒入到数据库中的词表
 * 
 * @author luogang
 * 
 */
public class DBUtil extends TestCase {
	public static Connection getOracleConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.5.253:1521:orcl", "weiwei2",
					"weiwei2");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	static final String baseDic = "coreDict.txt";

	public static void importWords() throws Exception {
		Connection conn = DicDBFactory.getConnect();
		conn.setAutoCommit(false);
		String dicDir = "./dic/";
		String path = dicDir + baseDic;

		InputStream file = new FileInputStream(new File(path));
		BufferedReader read = new BufferedReader(new InputStreamReader(file,
				"GBK"));

		String line = null;

		PreparedStatement stmt = conn
				.prepareStatement("insert into AI_BASEWORD(WORD,PARTSPEECH,FRQ,PINYIN)values(?,?,?,?)");
		int count = 0;
		while ((line = read.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line, ":");
			String word = st.nextToken(); // 单词文本
			String pos = st.nextToken();
			String frqStr = st.nextToken();
			
			String pinyin = null;
			if(st.hasMoreTokens())
				pinyin = st.nextToken();

			//stmt.setInt(1, count++);
			// String word = "test";
			stmt.setString(1, word);
			stmt.setString(2, pos);
			stmt.setInt(3, Integer.parseInt(frqStr));
			stmt.setString(4, pinyin);
			stmt.executeUpdate();

			System.out.println("词:" + word + "词性:" + pos);
		}
		read.close();
		conn.commit();
		conn.close();
	}

	public static void exportWords() {
		try {

			String fileName = "d:/lg/rrWords.txt";
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

			Connection conn = DicDBFactory.getConnect();

			String sql = "SELECT WORD,PARTSPEECH from AI_BASEWORD where AI_BASEWORD.PARTSPEECH='rr'";
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					String word = rs.getString(1);
					String pos = rs.getString(2);
					System.out.println(word + ":" + pos);

					bw.write(word + ":" + pos); // 写入一个字符串
					bw.write("\r\n"); // 写入换行符
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn.close();

			bw.close(); // 把缓存中的内容写入文件

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void exportAllWords() {
		try {
			String fileName = "d:/lg/baseWords.txt";
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

			Connection conn = DicDBFactory.getConnect();

			String sql = "SELECT WORD,PARTSPEECH from AI_BASEWORD";
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					String word = rs.getString(1);
					String pos = rs.getString(2);
					System.out.println(word + ":" + pos);

					bw.write(word + ":" + pos); // 写入一个字符串
					bw.write("\r\n"); // 写入换行符
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn.close();

			bw.close(); // 把缓存中的内容写入文件

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static void updateWords() throws Exception {
		Connection conn = DicDBFactory.getConnect();

		String path = "d:/lg/rrWords.txt";

		InputStream file = new FileInputStream(new File(path));
		BufferedReader read = new BufferedReader(new InputStreamReader(file,
				"UTF-8"));

		String line = null;

		PreparedStatement stmt = conn
				.prepareStatement("update AI_BASEWORD set AI_BASEWORD.PARTSPEECH=? where WORD =? and PARTSPEECH like 'r%'");
		int count = 0;
		while ((line = read.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line, ":");
			String word = st.nextToken(); // 单词文本
			String pos = st.nextToken();

			//stmt.setInt(1, count++);
			// String word = "test";
			stmt.setString(2, word);
			stmt.setString(1, pos);
			stmt.executeUpdate();

			System.out.println("词:" + word + "词性:" + pos);
		}
		read.close();

		conn.close();
	}

	public static void main(String[] args) throws Exception {
		// getConnect();
		// importWords();
		//exportWords();
		exportAllWords();
		//updateWords();
	}
}
