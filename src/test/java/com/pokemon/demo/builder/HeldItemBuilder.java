package com.pokemon.demo.builder;

import com.pokemon.demo.domain.model.HeldItem;

public class HeldItemBuilder extends Builder<HeldItem> {

	@Override
	public HeldItem build(int index) {
		return new HeldItem()
		  .setItem(new ResourceBuilder().build(index * 2))
		  .setVersionDetails(new HeldItemVersionBuilder().buildList(index));
	}

}
