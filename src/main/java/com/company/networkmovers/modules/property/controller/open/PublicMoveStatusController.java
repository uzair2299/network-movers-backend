package com.company.networkmovers.modules.property.controller.open;

import com.company.networkmovers.modules.property.service.MoveStatusService;
import com.company.networkmovers.shared.controller.AbstractPublicLookupController;
import com.company.networkmovers.modules.property.dto.request.MoveStatusRequest;
import com.company.networkmovers.modules.property.dto.response.MoveStatusResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/move-status")
public class PublicMoveStatusController extends AbstractPublicLookupController<MoveStatusRequest, MoveStatusResponse> {

    public PublicMoveStatusController(MoveStatusService service) {
        super(service);
    }
}
