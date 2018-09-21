import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FileDao {
	public static String openFile(String path) throws IOException {
		String txt = null;
		File file = new File(path);
		if (!file.exists()) {
			throw new IOException("읽고자 하는 파일이 존재하지 않습니다.");
		}

		BufferedReader in = null;
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis,"MS949");
		try {
			in = new BufferedReader(isr);
			char[] buffer = new char[(int) file.length()];
			int count = in.read(buffer);
			txt = new String(buffer, 0, count);
		} finally {
			if (in != null)
				in.close();
		}
		
		return txt;
		// String(byte[] bytes, Charset charset)
		// Constructs a new String by decoding the specified array of bytes using the
		// specified charset.

	}
	
	public static void saveFile(String path,String txt) throws IOException {
		File file = new File(path);
		if(file.exists()) {
			throw new IOException("존재하는 파일입니다. 다른 이름을 사용하세요");
		}
		
		PrintWriter out = null;
		try {
			out = new PrintWriter(file);
			txt = txt.replaceAll("\n", "\r\n");
			out.print(txt);
		}finally {
			if(out != null) out.close();
		}
	}
}
