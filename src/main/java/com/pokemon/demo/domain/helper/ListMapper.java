package com.pokemon.demo.domain.helper;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListMapper<F, T> {

	private final Mapper<F, T> mapper;

	public List<T> map(List<F> input) {
		return input.stream()
		  .map(this.mapper::map)
		  .toList();
	}

}