package parent.cycle;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;

import parent.cycle.parts.chainassembly.ChainAssemblyFactory;
import parent.cycle.parts.chainassembly.IChainAssembly;
import parent.cycle.parts.frames.FrameFactory;
import parent.cycle.parts.frames.IFrame;
import parent.cycle.parts.handlebars.HandleBarsFactory;
import parent.cycle.parts.handlebars.IHandleBar;
import parent.cycle.parts.seats.CycleSeatsFactory;
import parent.cycle.parts.seats.ISeatAssembly;
import parent.cycle.parts.wheels.CycleWheelsFactory;
import parent.cycle.parts.wheels.IWheels;

public class Cycle implements ICycle {

	private final IFrame frame;
	private final IHandleBar handleBar;
	private final ISeatAssembly seatAssembly;
	private final IWheels wheels;
	private final IChainAssembly chainAssembly;

	private Cycle(IFrame frame, IHandleBar handleBar, ISeatAssembly seatAssembly, IWheels wheels, IChainAssembly chainAssembly) {
		this.frame = frame;
		this.handleBar = handleBar;
		this.seatAssembly = seatAssembly;
		this.wheels = wheels;
		this.chainAssembly = chainAssembly;
	}

	@Override
	public int getPrice() throws Exception {
		return frame.getPrice() + handleBar.getPrice() + seatAssembly.getPrice() + wheels.getPrice() + chainAssembly.getPrice();
	}

	@Override
	public int getFramePrice() throws Exception {
		return frame.getPrice();
	}

	@Override
	public int getHandleBarPrice() throws Exception {
		return handleBar.getPrice();
	}

	@Override
	public int getSeatAssemblyPrice() throws Exception {
		return seatAssembly.getPrice();
	}

	@Override
	public int getWheelsPrice() throws Exception {
		return wheels.getPrice();
	}

	@Override
	public int getChainAssemblyPrice() throws Exception {
		return chainAssembly.getPrice();
	}

	public static class Builder {

		private IFrame frame;
		private IHandleBar handleBar;
		private ISeatAssembly seatAssembly;
		private IWheels wheels;
		private IChainAssembly chainAssembly;

		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		private Builder() {

			Date currentDate = new Date();

			this.frame = FrameFactory.getDefaultFrame(currentDate);
			this.handleBar = HandleBarsFactory.getDefaultHandleBar(currentDate);
			this.seatAssembly = CycleSeatsFactory.getDefaultCycleSeat(currentDate);
			this.wheels = CycleWheelsFactory.getDefaultCycleWheel(currentDate);
			this.chainAssembly = ChainAssemblyFactory.getDefaultCycleChainAssembly(currentDate);
		}

		public static Builder create() {
			return new Builder();
		}

		public Builder setFrame(JSONObject info) throws Exception {

			String type = (String) info.get("type");
			String date = (String) info.get("date");

			if (isEmpty(type) || isEmpty(date))
				return this;

			Date dateObj = simpleDateFormat.parse(date);

			this.frame = FrameFactory.getFrame(type, dateObj);
			return this;
		}

		public Builder setHandleBar(JSONObject info) throws Exception {

			String type = (String) info.get("type");
			String date = (String) info.get("date");

			if (isEmpty(type) || isEmpty(date))
				return this;

			Date dateObj = simpleDateFormat.parse(date);

			this.handleBar = HandleBarsFactory.getHandleBar(type, dateObj);
			return this;
		}

		public Builder setSeatAssembly(JSONObject info) throws Exception {

			String type = (String) info.get("type");
			String date = (String) info.get("date");

			if (isEmpty(type) || isEmpty(date))
				return this;

			Date dateObj = simpleDateFormat.parse(date);

			this.seatAssembly = CycleSeatsFactory.getCycleSeat(type, dateObj);
			return this;
		}

		public Builder setWheels(JSONObject info) throws Exception {

			String type = (String) info.get("type");
			String date = (String) info.get("date");

			if (isEmpty(type) || isEmpty(date))
				return this;

			Date dateObj = simpleDateFormat.parse(date);

			this.wheels = CycleWheelsFactory.getCycleWheel(type, dateObj);
			return this;
		}

		public Builder setChainAssembly(JSONObject info) throws Exception {

			String type = (String) info.get("type");
			String date = (String) info.get("date");

			if (isEmpty(type) || isEmpty(date))
				return this;

			Date dateObj = simpleDateFormat.parse(date);

			this.chainAssembly = ChainAssemblyFactory.getCycleChainAssembly(type, dateObj);
			return this;
		}

		public ICycle build() {
			return new Cycle(this.frame, this.handleBar, this.seatAssembly, this.wheels, this.chainAssembly);
		}

		private boolean isEmpty(String str) {
			if (str == null || str.trim().equals(""))
				return true;
			return false;
		}

	}

}
