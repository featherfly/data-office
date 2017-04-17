package cn.featherfly.data.impl;
//
//package com.cdthgk.data.record;
//
//import java.util.Collection;
//
//import com.cdthgk.common.bean.BeanUtils;
//import com.cdthgk.data.core.DataRecord;
//
//
///**
// * <p>
// * 对象数据记录
// * </p>
// *
// * @author 钟冀
// */
//public class ObjectDataRecord<O> implements DataRecord{
//
//	private O object;
//
//
//	/**
//	 * @param object 对象
//	 */
//	public ObjectDataRecord(O object) {
//		this.object = object;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public <E> E get(int index) {
//		return null;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public <E> E get(String key) {
//		return (E) BeanUtils.getProperty(object, key);
//	}
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Collection<?> getValues() {
//		return null;
//	}
//
//	/**
//	 * 返回object
//	 * @return object
//	 */
//	public O getObject() {
//		return object;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public int getValuesNumber() {
//		// YUFEI_TODO Auto-generated method stub
//		return 0;
//	}
//}
