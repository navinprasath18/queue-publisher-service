package com.sap.queuepublisher.mockted;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ted")
public class TedMockController {

	@GetMapping
	public JSONObject checkStatus(@RequestBody String body) {
		String jsonStr = "{\n" + "  \"groupName\": \"TED1\",\n" + "  \"document\" : {\n"
				+ "    \"transactionId\": \"afd53ab8-0456-4ssd-8539-3f64c324a4f8\",\n"
				+ "    \"documentId\": \"DOCUMENT1\",\n" + "    \"submissionId\": \"TED_SUBMISSION1\",\n"
				+ "    \"documentType\": \"Invoice\",\n" + "    \"senderId\": \"SENDER1\",\n"
				+ "    \"receiverId\": \"RECEIVER1\",\n" + "    \"acknowledged\": false,\n"
				+ "    \"createdTime\": \"2021-10-23T18:25:43.511Z\"\n" + "  },\n" + "  \"ratelimit\": {\n"
				+ "    \"limitPerDay\" : 9000,\n" + "    \"remainingPerDay\" : 8980,\n"
				+ "    \"resetTime\" : 1633382655\n" + "  },\n" + "  \"status\": {\n" + "    \"code\" : \"\",\n"
				+ "    \"error\": \"\",\n" + "    \"exception\": \"\",\n" + "    \"message\" : \"\"\n" + "  }\n" + "}";
		JSONObject json = new JSONObject(jsonStr);
		return json;

	}

}
