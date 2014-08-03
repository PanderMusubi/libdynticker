package com.github.andrefbsantos.libdynticker.houbi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.andrefbsantos.libdynticker.core.ExchangeTest;
import com.github.andrefbsantos.libdynticker.core.Pair;

public class HuobiExchangeTest extends ExchangeTest {

	@Override
	@Before
	public void setUp() throws Exception {
		testExchange = new HuobiExchange();
	}

	@Override
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParseJson() {
		try {
			Pair pair = new Pair("BTC", "CNY");
			JsonNode node = (new ObjectMapper().readTree(new File("src/test/json/huobi-ticker.json")));
			String lastValue = testExchange.parseJSON(node, pair);
			Assert.assertEquals("3671.05", lastValue);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testGetPairs() {
		List<Pair> pairs;
		try {
			pairs = testExchange.getPairs();
			Assert.assertTrue(pairs.contains(new Pair("LTC", "CNY")));
			Assert.assertTrue(pairs.contains(new Pair("BTC", "CNY")));
			Assert.assertFalse(pairs.contains(new Pair("InvalidCoin", "BTC")));
		} catch (IOException e) {
			Assert.fail();
		}
	}

	@Test
	public void testGetLastValue() {
		double lastValue;
		List<Pair> pairs = new ArrayList<Pair>();

		pairs.add(new Pair("LTC", "CNY"));
		pairs.add(new Pair("BTC", "CNY"));

		try {
			for (Pair pair : pairs) {
				lastValue = testExchange.getLastValue(pair);
				Assert.assertNotNull(lastValue);
				// System.out.println(lastValue);
			}
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
