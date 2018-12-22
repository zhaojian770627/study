package zookeeper;

import java.io.FileOutputStream;
import java.io.IOException;

public class TestStat {

	public static void main(String[] args) throws IOException {
		FileOutputStream fps1 = new FileOutputStream("/home/zj/a.xml", true);
		XmlOutputArchive out = new XmlOutputArchive(fps1);
		Stat s = new Stat();
		s.serialize(out, "abc");
		fps1.close();
	}

}
