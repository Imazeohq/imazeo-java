package com.imazeo.resource;
import com.imazeo.Version;
import com.imazeo.http.*;
import com.imazeo.model.*;
import java.util.*;
import java.util.function.Supplier;
public final class CreditsResource extends ProjectResource {
  public CreditsResource(Transport http, Supplier<String> projectId) {super(http,projectId);}
  public CreditQuote quote(CreditQuoteInput input) { return json("POST",new String[]{"credits","quote"},input,null,null,CreditQuote.class); }
}
