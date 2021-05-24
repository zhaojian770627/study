package com.zj.study.javacc.e1;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ParserTest {
	private SimpleCharStream charStream;
	ReportFormulParser parser;

	public static void main(String[] args) throws UnsupportedEncodingException, ParseException {
		ParserTest parserTest = new ParserTest();
		String formulStr1 = "GLOpenBal(junru,2020-01,6601,dept=kfb&&supper=ddd,dr,fsbz,fhbz,includeUntaller,includeSYpz,includeErrorPz)";
		String formulStr2 = "GLCloseBal(junru,2020-01,6601,dept=kfb&&supper=ddd,dr,fsbz,fhbz,includeUntaller,includeSYpz,includeErrorPz)";
		String formulStr3 = "GLAMT(junru,2020-01,6601,dept=kfb&&supper=ddd,dr,fsbz,fhbz,includeUntaller,includeSYpz,includeErrorPz)";

		parserTest.parser(formulStr1);
		System.out.println("-----------------------------------");
		parserTest.parser(formulStr2);
		System.out.println("-----------------------------------");
		parserTest.parser(formulStr3);
	}

	private void parser(String formulStr) throws UnsupportedEncodingException, ParseException {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(formulStr.getBytes("UTF-8"));
		if (parser == null) {
			charStream = new SimpleCharStream(inputStream, null, 1, 1);
			ReportFormulParserTokenManager tokenManager = new ReportFormulParserTokenManager(charStream);
			parser = new ReportFormulParser(tokenManager);
		} else {
			charStream.ReInit(inputStream);
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		parser.parser(paramMap);
		Tools.print(paramMap);
	}
}
