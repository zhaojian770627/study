package com.zj.study.freemarker.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.study.freemarker.entity.DataBase;
import com.zj.study.freemarker.entity.Settings;
import com.zj.study.freemarker.entity.Table;
import com.zj.study.freemarker.utils.DataBaseUtils;
import com.zj.study.freemarker.utils.PropertiesUtils;

public class GeneratorFacade {
	private String templatePath;
	private String outPath;
	private Settings settings;
	private DataBase db;
	Generator generator;

	public GeneratorFacade(String templatePath, String outPath, Settings settings, DataBase db) throws IOException {
		super();
		this.templatePath = templatePath;
		this.outPath = outPath;
		this.settings = settings;
		this.db = db;

		this.generator = new Generator(templatePath, outPath);
	}

	public void generatorByDataBase() throws Exception {
		List<Table> tables = DataBaseUtils.getDBTables(db);
		for (Table table : tables) {
			// 对每一个Table对象进行代码生成
			Map<String, Object> dataModel = getDataModel(table);

			generator.scanAndGenerator(dataModel);
		}
	}

	private Map<String, Object> getDataModel(Table table) {
		Map<String, Object> dataModel = new HashMap<>();
		// 自定义配置
		dataModel.putAll(PropertiesUtils.customMap);
		// 元数据
		dataModel.put("table", table);
		// setting
		dataModel.putAll(this.settings.getSettingMap());
		// 类型
		dataModel.put("ClassName", table.getName2());
		return dataModel;
	}

	public static void main(String[] args) throws Exception {
		String templatePath = "D:\\tmp\\template";
		String outPath = "D:\\tmp\\out";
		Settings settings = new Settings("zj", "com.zj", "zj freemark test", "zj");
		DataBase db = new DataBase("MYSQL", "test");
		db.setUserName("root");
		db.setPassword("root");

		GeneratorFacade facade = new GeneratorFacade(templatePath, outPath, settings, db);
		facade.generatorByDataBase();

	}
}
