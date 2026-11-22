package com.imazeo.resource;
import com.imazeo.Version;
import com.imazeo.http.*;
import com.imazeo.model.*;
import java.util.*;
import java.util.function.Supplier;
public final class OutputsResource extends ProjectResource {
  public OutputsResource(Transport http, Supplier<String> projectId) {super(http,projectId);}
  public OutputsSummary summary() { return json("GET",new String[]{"outputs","summary"},null,null,null,OutputsSummary.class); }
  public Page<OutputRow> list(Map<String,String> query) { return page(new String[]{"outputs"},query,OutputRow[].class); }
}
