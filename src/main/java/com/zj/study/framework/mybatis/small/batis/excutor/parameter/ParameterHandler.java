package com.zj.study.framework.mybatis.small.batis.excutor.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParameterHandler {

	  void setParameters(PreparedStatement ps)  throws SQLException;

}
