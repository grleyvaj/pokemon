package com.pokemon.demo.builder;

import com.pokemon.demo.application.ports.webservice.resources.get_held_item.response.HeldItemResponseSoap;

public class HeldItemResponseSoapBuilder extends Builder<HeldItemResponseSoap> {

	@Override
	public HeldItemResponseSoap build(int index) {
		return new HeldItemResponseSoap()
		  .setItem(new ResourceResponseSoapBuilder().build(index * 2))
		  .setVersionDetails(new HeldItemVersionResponseSoapBuilder().buildList(index));
	}

}
