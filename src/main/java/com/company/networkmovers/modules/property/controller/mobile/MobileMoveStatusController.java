package com.company.networkmovers.modules.property.controller.mobile;

import com.company.networkmovers.modules.property.service.MoveStatusService;
import com.company.networkmovers.shared.controller.AbstractMobileLookupController;
import com.company.networkmovers.modules.property.dto.request.MoveStatusRequest;
import com.company.networkmovers.modules.property.dto.response.MoveStatusResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mobile/move-status")
public class MobileMoveStatusController extends AbstractMobileLookupController<MoveStatusRequest, MoveStatusResponse> {

    public MobileMoveStatusController(MoveStatusService service) {
        super(service);
    }
}
