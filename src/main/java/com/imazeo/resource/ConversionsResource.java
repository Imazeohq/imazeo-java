package com.imazeo.resource;
import com.imazeo.Version;
import com.imazeo.http.*;
import com.imazeo.model.*;
import java.util.*;
import java.util.function.Supplier;
public final class ConversionsResource extends ProjectResource {
  public ConversionsResource(Transport http, Supplier<String> projectId) {super(http,projectId);}
  public ConversionCapabilities capabilities() { return json("GET",new String[]{"conversions","capabilities"},null,null,null,ConversionCapabilities.class); }
  public ConversionJob get(String id) { return json("GET",new String[]{"conversions",id},null,null,null,ConversionJob.class); }
  public ConversionJob cancel(String id) { return json("POST",new String[]{"conversions",id,"cancel"},null,null,null,ConversionJob.class); }
  public ToolArtifact save(String id) { return json("POST",new String[]{"conversions",id,"save"},null,null,null,ToolArtifact.class); }
  public BinaryResult download(String id) { return binary(new String[]{"conversions",id,"download"},null); }
  public List<ConversionJob> list(String engine) {return Arrays.asList(json("GET",new String[]{"conversions"},null,Map.of("engine",engine),null,ConversionJob[].class));}
  public ConversionJob lookup(String key) {return json("GET",new String[]{"conversions","lookup"},null,Map.of("key",key),null,ConversionJob.class);}
  public ConversionJob website(String sourceUrl, ProcessingOptions request, WebsiteOptions options) {return json("POST",new String[]{"conversions","website"},Map.of("url",sourceUrl),query(options),request,ConversionJob.class);}
  private ConversionJob upload(String engine, UploadSource source, ProcessingOptions request, Object options) {
    MultipartBody form=MultipartBody.builder().addFile("file",source.data(),source.filename()!=null?source.filename():"document."+engine,source.contentType()).build();
    String[] parts=engine.equals("docx")?new String[]{"conversions"}:new String[]{"conversions",engine};
    return http.requestMultipartJson("POST",url(parts,query(options)),form,source.data().length,ConversionJob.class,Version.DEFAULT_TIMEOUT_MS,request.headers()).data();
  }
  public ConversionJob docx(UploadSource source, ProcessingOptions request, DocxOptions options) {return upload("docx",source,request,options);}
  public ConversionJob xlsx(UploadSource source, ProcessingOptions request, XlsxOptions options) {return upload("xlsx",source,request,options);}
  public ConversionJob pptx(UploadSource source, ProcessingOptions request, PptxOptions options) {return upload("pptx",source,request,options);}
}
