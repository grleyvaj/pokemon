package com.pokemon.demo.domain.helper;

public interface Mapper<F, T> {

	T map(F input);
}
