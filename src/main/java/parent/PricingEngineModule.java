package parent;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import parent.cycle.Cycle;
import parent.cycle.ICycle;

public class PricingEngineModule {

	public static void main(String[] args) throws Exception {

		String path = "src/main/resources/cyclesDetails.json";

		File file = new File(path);
		
		String filePath = file.getAbsolutePath();

		if (args.length > 0)
			filePath = args[0];

		JSONArray listOfCycleDetails = (JSONArray) new JSONParser().parse(new FileReader(filePath));

		PricingEngineModule demo = new PricingEngineModule();
		
		JSONArray cyclePriceDetails = demo.getAllCyclePriceDetails(listOfCycleDetails);

		System.out.println(cyclePriceDetails.toJSONString());

	}

	public JSONArray getAllCyclePriceDetails(JSONArray listOfCycleDetails) throws Exception {

		ExecutorService service = Executors.newFixedThreadPool(10);
		List<Future<JSONObject>> details = new ArrayList();

		for (Object cycleDetails : listOfCycleDetails) {
			Future<JSONObject> cyclePriceDetail = service.submit(() -> this.getCyclePriceDetail((JSONObject) cycleDetails));
			details.add(cyclePriceDetail);
		}

		service.shutdown();

		try {

			if (!service.awaitTermination(5, TimeUnit.MINUTES)) {
				service.shutdownNow();
			}

		} catch (InterruptedException e) {
			service.shutdownNow();
		}

		JSONArray cyclePriceDetails = new JSONArray();

		for (Future<JSONObject> cyclePriceDetail : details) {
			JSONObject priceDetail = cyclePriceDetail.get();
			cyclePriceDetails.add(priceDetail);
		}

		return cyclePriceDetails;

	}

	public JSONObject getCyclePriceDetail(JSONObject cycleDetails) throws Exception {

		JSONObject cyclePrice = new JSONObject();

		ICycle cycle = Cycle.Builder.create()
				.setFrame((JSONObject) cycleDetails.get("frame"))
				.setHandleBar((JSONObject) cycleDetails.get("handle-bar"))
				.setSeatAssembly((JSONObject) cycleDetails.get("seat"))
				.setWheels((JSONObject) cycleDetails.get("wheel"))
				.setChainAssembly((JSONObject) cycleDetails.get("chain-assembly")).build();

		cyclePrice.put("cycle price", cycle.getPrice());

		cyclePrice.put("frame price", cycle.getFramePrice());
		cyclePrice.put("handle bar price", cycle.getHandleBarPrice());
		cyclePrice.put("seats price", cycle.getSeatAssemblyPrice());
		cyclePrice.put("wheels price", cycle.getWheelsPrice());
		cyclePrice.put("chain assembly price", cycle.getChainAssemblyPrice());

		return cyclePrice;

	}

}
