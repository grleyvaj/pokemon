package com.pokemon.demo.builder;

import com.pokemon.demo.application.ports.webservice.resources.get_held_item.response.HeldItemVersionResponseSoap;

public class HeldItemVersionResponseSoapBuilder extends Builder<HeldItemVersionResponseSoap> {

	@Override
	public HeldItemVersionResponseSoap build(int index) {
		return new HeldItemVersionResponseSoap()
		  .setVersion(new ResourceResponseSoapBuilder().build(index * 2))
		  .setRarity(index);
	}

}
