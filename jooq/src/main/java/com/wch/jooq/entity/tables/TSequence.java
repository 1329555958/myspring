/**
 * This class is generated by jOOQ
 */
package com.wch.jooq.entity.tables;


import com.wch.jooq.entity.Keys;
import com.wch.jooq.entity.Optimus;
import com.wch.jooq.entity.tables.records.TSequenceRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.0"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TSequence extends TableImpl<TSequenceRecord> {

	private static final long serialVersionUID = 559532038;

	/**
	 * The reference instance of <code>optimus.t_sequence</code>
	 */
	public static final TSequence T_SEQUENCE = new TSequence();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<TSequenceRecord> getRecordType() {
		return TSequenceRecord.class;
	}

	/**
	 * The column <code>optimus.t_sequence.NAME</code>. 名称
	 */
	public final TableField<TSequenceRecord, String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "名称");

	/**
	 * The column <code>optimus.t_sequence.CURRENT_VALUE</code>. 当前值
	 */
	public final TableField<TSequenceRecord, Long> CURRENT_VALUE = createField("CURRENT_VALUE", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaulted(true), this, "当前值");

	/**
	 * The column <code>optimus.t_sequence.INCREMENT</code>. 增长步长
	 */
	public final TableField<TSequenceRecord, Short> INCREMENT = createField("INCREMENT", org.jooq.impl.SQLDataType.SMALLINT.nullable(false).defaulted(true), this, "增长步长");

	/**
	 * The column <code>optimus.t_sequence.TOTAL</code>. 单次取值总量，更新总量需重启应用
	 */
	public final TableField<TSequenceRecord, Short> TOTAL = createField("TOTAL", org.jooq.impl.SQLDataType.SMALLINT.nullable(false).defaulted(true), this, "单次取值总量，更新总量需重启应用");

	/**
	 * The column <code>optimus.t_sequence.THRESHOLD</code>. 刷新阀值，更新阀值需重启应用
	 */
	public final TableField<TSequenceRecord, Short> THRESHOLD = createField("THRESHOLD", org.jooq.impl.SQLDataType.SMALLINT.nullable(false).defaulted(true), this, "刷新阀值，更新阀值需重启应用");

	/**
	 * Create a <code>optimus.t_sequence</code> table reference
	 */
	public TSequence() {
		this("t_sequence", null);
	}

	/**
	 * Create an aliased <code>optimus.t_sequence</code> table reference
	 */
	public TSequence(String alias) {
		this(alias, T_SEQUENCE);
	}

	private TSequence(String alias, Table<TSequenceRecord> aliased) {
		this(alias, aliased, null);
	}

	private TSequence(String alias, Table<TSequenceRecord> aliased, Field<?>[] parameters) {
		super(alias, Optimus.OPTIMUS, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<TSequenceRecord> getPrimaryKey() {
		return Keys.KEY_T_SEQUENCE_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<TSequenceRecord>> getKeys() {
		return Arrays.<UniqueKey<TSequenceRecord>>asList(Keys.KEY_T_SEQUENCE_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TSequence as(String alias) {
		return new TSequence(alias, this);
	}

	/**
	 * Rename this table
	 */
	public TSequence rename(String name) {
		return new TSequence(name, null);
	}
}
