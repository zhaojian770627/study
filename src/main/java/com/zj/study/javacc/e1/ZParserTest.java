package com.zj.study.javacc.e1;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ZParserTest {
	private SimpleCharStream charStream;
	ReportFormulParser cachedParser;

	public static void main(String[] args) throws UnsupportedEncodingException, ParseException {
		ZParserTest parserTest = new ZParserTest();
		String formulStr1 = "GLOpenBal(\"0119_0001_01\",\"2021-03\",\"1001\",[\"dept\"=\"aaa\"],\"Debit\",\"bb\",\"bb\",Y,Y,Y)";
		
		parserTest.parser(formulStr1);
	}

	private void parser(String formulStr) throws UnsupportedEncodingException, ParseException {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(formulStr.getBytes("UTF-8"));
		if (cachedParser == null) {
			charStream = new SimpleCharStream(inputStream, null, 1, 1);
			ReportFormulParserTokenManager tokenManager = new ReportFormulParserTokenManager(charStream);
			cachedParser = new ReportFormulParser(tokenManager);
		} else {
			charStream.ReInit(inputStream);
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		cachedParser.parser(paramMap);
		ZTools.print(paramMap);
	}
}
