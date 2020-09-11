package parent.cycle.parts.handlebars;

import java.util.Date;

public class HandleBarsFactory {

	public static IHandleBar getHandleBar(String type, Date pricingDate) {

		switch (type) {
		case "flatHandleBar":
			return new FlatHandleBar(pricingDate);
		case "DropHandleBar":
			return new DropHandleBar(pricingDate);
		default:
			return new FlatHandleBar(pricingDate);
		}

	}
	
	public static IHandleBar getDefaultHandleBar(Date pricingDate) {
		return getHandleBar("flatHandleBar", pricingDate);
	}

}
