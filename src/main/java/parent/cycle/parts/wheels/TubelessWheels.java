package parent.cycle.parts.wheels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import parent.cycle.parts.Range;

public class TubelessWheels implements IWheels {

	private static NavigableMap<Date, Range> datePricingContext = new TreeMap<Date, Range>();

	private Date priceDate;

	static {

		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		try {

			datePricingContext.put(simpleDateFormat.parse("01/01/2013"), new Range(simpleDateFormat.parse("31/12/2015"), 200));
			datePricingContext.put(simpleDateFormat.parse("01/01/2016"), new Range(simpleDateFormat.parse("31/12/2018"), 250));
			datePricingContext.put(simpleDateFormat.parse("01/01/2019"), new Range(simpleDateFormat.parse("31/12/2019"), 325));
			datePricingContext.put(simpleDateFormat.parse("01/01/2020"), new Range(null, 400));

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public TubelessWheels(Date priceDate) {
		this.priceDate = priceDate;
	}

	@Override
	public int getPrice() throws Exception {

		Map.Entry<Date, Range> entry = datePricingContext.floorEntry(priceDate);

		if (entry == null)
			throw new Exception("Product doesn't exist till date: " + priceDate.toString());
		else
			return entry.getValue().getPrice();

	}

}
