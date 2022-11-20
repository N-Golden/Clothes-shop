package com.clotheshop.service;

public interface IConvertService<T, K> {
	T convertToDo(K k);
	K convertToEntity(T t);
}
