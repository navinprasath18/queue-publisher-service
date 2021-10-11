package com.sap.queuepublisher.pub;

import java.util.List;
import org.json.JSONObject;
import lombok.Data;

@Data
public class BodyJsonArray {

    List<JSONObject> json;

}
