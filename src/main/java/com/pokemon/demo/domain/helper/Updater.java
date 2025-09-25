package com.pokemon.demo.domain.helper;

public interface Updater<T, V> {

	T update(T entity, V data);

}
