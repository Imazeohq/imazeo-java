package com.imazeo.resource;
import com.imazeo.Version;
import com.imazeo.http.*;
import com.imazeo.model.*;
import java.util.*;
import java.util.function.Supplier;
public final class OperationsResource extends ProjectResource {
  public OperationsResource(Transport http, Supplier<String> projectId) {super(http,projectId);}
  public ProcessingStatus get(String id) { return json("GET",new String[]{"operations",id},null,null,null,ProcessingStatus.class); }
  public ProcessingStatus lookup(ProcessingLookupInput input) { return json("POST",new String[]{"operations","lookup"},input,null,null,ProcessingStatus.class); }
  public Object result(String id) {
          BinaryResult result = binary(new String[]{"operations",id,"result"},null);
          if(result.contentType().contains("application/json")) { try {return http.mapper().readTree(result.data()).get("data");} catch(java.io.IOException e){throw new com.imazeo.error.NetworkError("Response was not valid JSON",e);} } return result;
        }
}
