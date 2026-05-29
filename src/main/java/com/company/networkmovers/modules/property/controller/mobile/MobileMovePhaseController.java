package com.company.networkmovers.modules.property.controller.mobile;

import com.company.networkmovers.modules.property.service.MovePhaseService;
import com.company.networkmovers.shared.controller.AbstractMobileLookupController;
import com.company.networkmovers.modules.property.dto.request.MovePhaseRequest;
import com.company.networkmovers.modules.property.dto.response.MovePhaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mobile/move-phase")
public class MobileMovePhaseController extends AbstractMobileLookupController<MovePhaseRequest, MovePhaseResponse> {

    public MobileMovePhaseController(MovePhaseService service) {
        super(service);
    }
}
