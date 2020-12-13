package com.zj.study.orm.xmlparser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FactoryBuilder {
	private Map<String, BeanInfo> beanMap;

	public Factory newFactory() {
		return new Factory();
	}

	public class Factory {

		public Object getBean(String id) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
				NoSuchFieldException, SecurityException {
			BeanInfo beanInfo = beanMap.get(id);
			Class cls = Class.forName(beanInfo.getClazz());
			Object obj = cls.newInstance();
			for (PropsInfo pi : beanInfo.getProps()) {
				Field field = cls.getDeclaredField(pi.getName());
				field.setAccessible(true);

				if ("long".equals(pi.getType()))
					field.set(obj, Long.parseLong(pi.getValue()));
				else if ("float".equals(pi.getType()))
					field.set(obj, Float.parseFloat(pi.getValue()));
				else if ("int".equals(pi.getType()))
					field.set(obj, Integer.parseInt(pi.getValue()));
				else
					field.set(obj, pi.getValue());
			}
			return obj;
		}

	}

	public FactoryBuilder(String xmlPath)
			throws ParserConfigurationException, SAXException, FileNotFoundException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();

		SAXParser parser = factory.newSAXParser();

		parser.parse(new FileInputStream(xmlPath), new DefaultHandler() {
			private BeanInfo beanInfo;

			@Override
			public void startDocument() throws SAXException {
				beanMap = new HashMap<String, BeanInfo>();
			}

			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes)
					throws SAXException {
				if ("bean".equals(qName)) {
					beanInfo = new BeanInfo();
					beanInfo.setId(attributes.getValue("id"));
					beanInfo.setClazz(attributes.getValue("clazz"));
					beanInfo.setScope(attributes.getValue("scope"));

					beanInfo.setProps(new ArrayList<PropsInfo>());
				} else if ("property".equals(qName)) {
					beanInfo.getProps().add(new PropsInfo(attributes.getValue("name"), attributes.getValue("value"),
							attributes.getValue("type")));
				}
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				if ("bean".equals(qName)) {
					beanMap.put(beanInfo.getId(), beanInfo);
					beanInfo = null;
				}
			}

		});
	}
}
