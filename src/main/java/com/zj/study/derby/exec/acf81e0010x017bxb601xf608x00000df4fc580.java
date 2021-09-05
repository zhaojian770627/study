package com.zj.study.derby.exec;

import org.apache.derby.iapi.error.StandardException;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.types.NumberDataValue;
import org.apache.derby.impl.sql.execute.BaseActivation;
import org.apache.derby.impl.sql.execute.CursorActivation;

public final class acf81e0010x017bxb601xf608x00000df4fc580 extends CursorActivation {
	public void postConstructor() throws StandardException {
		setParameterValueSet(1, false);
		this.e0 = new Qualifier[1][];

		BaseActivation.allocateQualArray(this.e0, 0, 1);
		BaseActivation.setQualifier(this.e0,
				getExecutionFactory().getQualifier(0, 2, getMethod("e0"), this, false, false, false, 2), 0, 0);

		this.row = new org.apache.derby.iapi.sql.execute.ExecRow[2];
	}

	private Qualifier[][] e0;

	protected ResultSet createResultSet() throws StandardException {
		return getResultSetFactory()
				.getScrollInsensitiveResultSet(
						getResultSetFactory().getBulkTableScanResultSet(this, 'Ґ', 2, 1, 1, null, -1, null, -1, true,
								this.e0, "DERBYTABLE", null, null, false, false, -1, -1, 7, false, 0, 16, false, false,
								0.7000000000000001D, 59.564D),
						this, 0, 2, getScrollable(), 0.7000000000000001D, 59.564D);
	}

	protected void reinit() throws StandardException {
		throwIfMissingParms();
		BaseActivation.reinitializeQualifiers(this.e0);
	}

	public Object e0() throws StandardException {
		return (NumberDataValue) getParameter(0);
	}
}
