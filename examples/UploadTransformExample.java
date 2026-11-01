import com.imazeo.Imazeo;
import com.imazeo.Options;
import com.imazeo.model.TransformRequest;
import com.imazeo.model.TransformSpec;
import com.imazeo.model.UploadSource;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Upload an image and run an ephemeral transform.
 * Usage: java … UploadTransformExample &lt;image-path&gt;
 */
public final class UploadTransformExample {
  public static void main(String[] args) throws Exception {
    String apiKey = System.getenv("IMAZEO_API_KEY");
    if (apiKey == null || apiKey.isBlank()) {
      throw new IllegalStateException("Set IMAZEO_API_KEY");
    }
    if (args.length < 1) {
      throw new IllegalArgumentException("Usage: UploadTransformExample <image>");
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
    byte[] bytes = Files.readAllBytes(Path.of(args[0]));
    var image =
        client.images.upload(
            UploadSource.of(bytes, "input.bin", "application/octet-stream"));
    System.out.println("uploaded " + image.getId());

    var transformed =
        client.transforms.create(
            new TransformRequest()
                .setImageId(image.getId())
                .setMode("EPHEMERAL")
                .setTransform(
                    new TransformSpec().setWidth(800).setFormat("webp").setQuality(80)));
    Files.write(Path.of("out.webp"), transformed.data());
    System.out.println("wrote out.webp " + transformed.transformationId());
  }
}
