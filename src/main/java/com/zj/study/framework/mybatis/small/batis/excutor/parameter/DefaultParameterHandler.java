package com.zj.study.framework.mybatis.small.batis.excutor.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DefaultParameterHandler implements ParameterHandler {
	private Object parameter;

	public DefaultParameterHandler(Object parameter) {
		super();
		this.parameter = parameter;
	}

	@Override
	public void setParameters(PreparedStatement ps) throws SQLException {
		if (parameter == null) {
			return;
		}

		if (parameter.getClass().isArray()) {
			Object[] paramArray = (Object[]) parameter;
			for (int i = 0; i < paramArray.length; i++) {
				if (paramArray[i] instanceof Integer) {
					ps.setInt(i + 1, (int) paramArray[i]);
				} else if (paramArray[i] instanceof String) {
					ps.setString(i + 1, (String) paramArray[i]);
				} else if (paramArray[i] instanceof Long) {
					ps.setLong(i + 1, (long) paramArray[i]);
				}
			}
		}
	}

}
