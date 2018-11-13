package wang.jinggo.ES.cnword.ingest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.bulk.byscroll.ScrollableHitSource.Response;
import org.elasticsearch.action.ingest.PutPipelineRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.bytes.BytesArray;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.ingest.CompoundProcessor;
import org.elasticsearch.ingest.Pipeline;
import org.elasticsearch.ingest.PipelineStore;
import org.elasticsearch.ingest.Processor;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class TesIingest {

	public static void main(String[] args) throws IOException,
			InterruptedException, ExecutionException {

		Settings settings = Settings.builder()
				.put("cluster.name", "ElasticSearchClusterName").build();
		Client client = new PreBuiltTransportClient(settings);

		BytesReference source = jsonBuilder().startObject()
				.field("description", "my_pipeline").startArray("processors")
				.startObject().startObject("Lowercase").endObject().endObject()
				.endArray().endObject().bytes();
		PutPipelineRequest request = new PutPipelineRequest("1",
				source,
				XContentType.JSON);
		client.admin().cluster().putPipeline(request).get();
		
		String pipeline = "my_pipeline";
		client.prepareIndex().setPipeline(pipeline);

		// Processor processor = null;
		// Pipeline pipeline = new Pipeline("_id", "_description", null, new
		// CompoundProcessor(processor, processor));

		// PipelineStore store = new PipelineStore(Settings.EMPTY, null);
	}
	
	/*public static void testSim(){
		
		String json = null;

		Settings settings = Settings.builder()
				.put("cluster.name", "ElasticSearchClusterName").build();
		Client client = new PreBuiltTransportClient(settings);

		//rest client
        Response response = client.performRequest("POST", "/_ingest/pipeline/_simulate",null,null);
	}*/
	
}
