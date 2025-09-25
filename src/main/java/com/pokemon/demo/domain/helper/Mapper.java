package com.pokemon.demo.domain.helper;

public interface Mapper<T, F> {

	F map(T input);
}
