package com.zj.study.derby.exec;
import org.apache.derby.iapi.error.StandardException;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.impl.sql.execute.BaseActivation;

public final class ac560740aax017ax71cax4977x00000bc690d00 extends BaseActivation {
	public void postConstructor() throws StandardException {
		this.e0 = getExecutionFactory().getValueRow(2);
		this.e0.setColumn(1, (DataValueDescriptor) getDataValueFactory().getDataValue(1, null));
		this.e0.setColumn(2, (DataValueDescriptor) getDataValueFactory().getVarcharDataValue("tom", null));
		this.row = new ExecRow[2];
	}

	private ExecRow e0;

	protected ResultSet createResultSet() throws StandardException {
		return getResultSetFactory().getInsertResultSet(getResultSetFactory().getNormalizeResultSet(
				getResultSetFactory().getRowResultSet(this, getMethod("e0"), true, 1, 1.0D, 0.0D), 0, 0, 1.0D, 0.0D,
				false), null, null, -1, "APP", "DERBYTABLE");
	}

	public Object e0() throws StandardException {
		return this.e0;
	}
}
