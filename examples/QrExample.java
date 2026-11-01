import com.imazeo.Imazeo;
import com.imazeo.Options;
import com.imazeo.model.QrGenerateInput;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Minimal server-side example.
 * Requires: IMAZEO_API_KEY, optional IMAZEO_BASE_URL, IMAZEO_PROJECT_ID
 *
 * <p>Compile against the installed SDK jar, then run with the classpath.
 */
public final class QrExample {
  public static void main(String[] args) throws Exception {
    String apiKey = System.getenv("IMAZEO_API_KEY");
    if (apiKey == null || apiKey.isBlank()) {
      throw new IllegalStateException("Set IMAZEO_API_KEY");
    }

    Options.Builder builder =
        Options.builder()
            .apiKey(apiKey)
            .baseUrl(
                System.getenv("IMAZEO_BASE_URL") != null
                    ? System.getenv("IMAZEO_BASE_URL")
                    : "http://localhost:3001");
    String projectId = System.getenv("IMAZEO_PROJECT_ID");
    if (projectId != null && !projectId.isBlank()) {
      builder.projectId(projectId);
    }

    Imazeo client = new Imazeo(builder.build());
    var ctx = client.context.get();
    System.out.println("project " + ctx.getProjectId() + " env " + ctx.getEnvironment());

    var qr =
        client.qr.generate(
            new QrGenerateInput("https://imazeo.com").setSize(256).setFormat("png"));
    Files.write(Path.of("qr.png"), qr.data());
    System.out.println("wrote qr.png " + qr.contentType());
  }
}
