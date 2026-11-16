package com.imazeo.resource;
import com.imazeo.Version;
import com.imazeo.http.Transport;
import com.imazeo.model.*;
import java.util.*;
import java.util.function.Supplier;
abstract class ProjectResource {
  protected final Transport http;
  private final Supplier<String> projectId;
  ProjectResource(Transport http, Supplier<String> projectId) { this.http=http; this.projectId=projectId; }
  protected String url(String[] parts, Map<String,String> query) {
    List<String> all=new ArrayList<>(List.of("projects",projectId.get())); all.addAll(List.of(parts));
    return Transport.appendQuery(http.path(all.toArray(String[]::new)),query);
  }
  protected <T> T json(String method,String[] parts,Object body,Map<String,String> query,ProcessingOptions options,Class<T> type) {
    return http.requestJson(method,url(parts,query),body,type,Version.LIGHT_TIMEOUT_MS,options==null?Map.of():options.headers()).data();
  }
  protected <T> Page<T> page(String[] parts,Map<String,String> query,Class<T[]> type) {
    var result=http.requestJson("GET",url(parts,query),null,type,Version.LIGHT_TIMEOUT_MS);
    var meta=result.meta(); List<T> items=Arrays.asList(result.data());
    return new Page<>(items,meta.page()!=null?meta.page():1,meta.limit()!=null?meta.limit():20,meta.total()!=null?meta.total():items.size(),meta.pages()!=null?meta.pages():1,meta.requestId());
  }
  protected BinaryResult binary(String[] parts,Map<String,String> query) { return http.requestBinary("GET",url(parts,query),null,null,Version.DEFAULT_TIMEOUT_MS); }
  protected Map<String,String> query(Object value) {
    Map<String,String> result=new LinkedHashMap<>();
    if(value!=null) http.mapper().valueToTree(value).fields().forEachRemaining(e->{if(!e.getValue().isNull())result.put(e.getKey(),e.getValue().asText());});
    return result;
  }
}
