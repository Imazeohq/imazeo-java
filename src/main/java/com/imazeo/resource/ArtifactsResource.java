package com.imazeo.resource;
import com.imazeo.Version;
import com.imazeo.http.*;
import com.imazeo.model.*;
import java.util.*;
import java.util.function.Supplier;
public final class ArtifactsResource extends ProjectResource {
  public ArtifactsResource(Transport http, Supplier<String> projectId) {super(http,projectId);}
  public Page<ToolArtifact> list(Map<String,String> query) { return page(new String[]{"tools","artifacts"},query,ToolArtifact[].class); }
  public ToolArtifact get(String id) { return json("GET",new String[]{"tools","artifacts",id},null,null,null,ToolArtifact.class); }
  public MessageResult delete(String id) { return json("DELETE",new String[]{"tools","artifacts",id},null,null,null,MessageResult.class); }
  public BinaryResult download(String id) { return binary(new String[]{"tools","artifacts",id,"content"},null); }
  public ToolArtifact save(UploadSource source, ArtifactSaveInput input) {
    var builder=MultipartBody.builder().addFile("file",source.data(),source.filename()!=null?source.filename():"output.bin",input.contentType()).addField("kind",input.kind()).addField("displayName",input.displayName()).addField("contentType",input.contentType());
    if(input.requestSnapshot()!=null) {try {builder.addField("requestSnapshot",http.mapper().writeValueAsString(input.requestSnapshot()));}catch(java.io.IOException e){throw new IllegalArgumentException("Invalid request snapshot",e);}}
    if(input.sourceImageId()!=null)builder.addField("sourceImageId",input.sourceImageId());
    return http.requestMultipartJson("POST",url(new String[]{"tools","artifacts"},null),builder.build(),source.data().length,ToolArtifact.class,Version.DEFAULT_TIMEOUT_MS).data();
  }
}
