package consumer;

import java.time.Duration;
import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController 
public class Controller {
	
	@GetMapping("/gethc")
	public Mono<ResponseEntity<String>> gealthCheck() throws Exception {
		return Mono.just(ResponseEntity.ok("TEST RESPONSE"));                  
	}
	
	@GetMapping("/apim-gw")
	public Mono<ResponseEntity<String>> getHealthCheckAPIM() throws Exception {
		return Mono.just(ResponseEntity.ok("TEST RESPONSE"));
	}
	
	@GetMapping("/kafka-gw")
	public Mono<ResponseEntity<String>> getHealthCheckKafka() throws Exception {
		return Mono.just(ResponseEntity.ok("TEST RESPONSE"));
	}
	
	/**
	 * [EKS] POD LivenessProbe 헬스체크
	 */
	private final Instant started = Instant.now();
	
    @GetMapping("/healthz")
    public ResponseEntity<String> healthCheck() {
        Duration duration = Duration.between(started, Instant.now());
        if (duration.getSeconds() > 10) {
            return ResponseEntity.status(500).body("error: " + duration.getSeconds());
        } else {
            return ResponseEntity.ok("ok");
        }
    }

}
