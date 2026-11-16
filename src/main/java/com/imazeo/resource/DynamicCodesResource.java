package com.imazeo.resource;
import com.imazeo.Version;
import com.imazeo.http.*;
import com.imazeo.model.*;
import java.util.*;
import java.util.function.Supplier;
public final class DynamicCodesResource extends ProjectResource {
  public DynamicCodesResource(Transport http, Supplier<String> projectId) {super(http,projectId);}
  public DynamicCapabilities capabilities() { return json("GET",new String[]{"dynamic-codes","capabilities"},null,null,null,DynamicCapabilities.class); }
  public Page<DynamicCode> list(Map<String,String> query) { return page(new String[]{"dynamic-codes"},query,DynamicCode[].class); }
  public DynamicCode get(String id) { return json("GET",new String[]{"dynamic-codes",id},null,null,null,DynamicCode.class); }
  public DynamicCode create(DynamicCreateInput input, String idempotencyKey) { return json("POST",new String[]{"dynamic-codes"},input,null,new ProcessingOptions(idempotencyKey,null),DynamicCode.class); }
  public DynamicCode update(String id, DynamicEditInput input, String idempotencyKey) { return json("PATCH",new String[]{"dynamic-codes",id},input,null,new ProcessingOptions(idempotencyKey,null),DynamicCode.class); }
  public ScanAnalytics analytics(String id, Map<String,String> query) { return json("GET",new String[]{"dynamic-codes",id,"analytics"},null,query,null,ScanAnalytics.class); }
  public BinaryResult exportAnalytics(String id, Map<String,String> query) { return binary(new String[]{"dynamic-codes",id,"analytics","export"},query); }
}
