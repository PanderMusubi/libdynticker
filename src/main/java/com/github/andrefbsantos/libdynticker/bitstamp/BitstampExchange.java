package com.github.andrefbsantos.libdynticker.bitstamp;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;

import com.github.andrefbsantos.libdynticker.core.Exchange;
import com.github.andrefbsantos.libdynticker.core.Pair;

/**
 * Specialization of exchange for Bitstamp.com
 * 
 * @author andre
 * 
 * 
 */
public class BitstampExchange extends Exchange {

	@Override
	public List<Pair> getPairs() {
		List<Pair> pairs = new ArrayList<Pair>();
		pairs.add(new Pair("BTC", "USD"));
		return pairs;
	}

	@Override
	protected String getTickerURL(Pair pair) {
		return "https://www.bitstamp.net/api/ticker/";
	}

	@Override
	protected String parseJSON(JsonNode node, Pair pair) {
		return node.get("last").getTextValue();
	}
}
