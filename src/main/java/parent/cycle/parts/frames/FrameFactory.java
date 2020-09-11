package parent.cycle.parts.frames;

import java.util.Date;

public class FrameFactory {

	public static IFrame getFrame(String frameType, Date pricingDate) {

		switch (frameType) {

		case "steelFrame":
			return new SteelFrame(pricingDate);
		case "carbanFiberFrame":
			return new CarbonFiberFrame(pricingDate);
		default:
			return new SteelFrame(pricingDate);
		}

	}

	public static IFrame getDefaultFrame(Date pricingDate) {
		return getFrame("steelFrame", pricingDate);
	}

}
