package com.company.networkmovers.modules.property.controller.open;

import com.company.networkmovers.modules.property.service.MovePhaseService;
import com.company.networkmovers.shared.controller.AbstractPublicLookupController;
import com.company.networkmovers.modules.property.dto.request.MovePhaseRequest;
import com.company.networkmovers.modules.property.dto.response.MovePhaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/move-phase")
public class PublicMovePhaseController extends AbstractPublicLookupController<MovePhaseRequest, MovePhaseResponse> {

    public PublicMovePhaseController(MovePhaseService service) {
        super(service);
    }
}
