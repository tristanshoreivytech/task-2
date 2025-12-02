package au.com.telstra.simcardactivator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sim")
public class SimActivationController {

    private final ActuatorClient actuatorClient;

    public SimActivationController(ActuatorClient actuatorClient) {
        this.actuatorClient = actuatorClient;
    }

    @PostMapping("/activate")
    public ResponseEntity<ActuatorResponse> activateSim(@RequestBody ActivationRequest request) {
        boolean success = actuatorClient.activateSim(request.getIccid());

        // Print whether or not activation was successful
        System.out.println("Activation request for ICCID " + request.getIccid()
                + " (customer " + request.getCustomerEmail() + ") success=" + success);

        // Return a simple JSON body indicating success to the caller too
        return ResponseEntity.ok(new ActuatorResponse(success));
    }
}
