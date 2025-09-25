package com.pokemon.demo.domain.helper;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ListMapper<From, To> {

	private final Mapper<From, To> mapper;

	public List<To> map(List<From> input) {
		return input.stream()
		  .map(this.mapper::map)
		  .collect(Collectors.toList());
	}

}