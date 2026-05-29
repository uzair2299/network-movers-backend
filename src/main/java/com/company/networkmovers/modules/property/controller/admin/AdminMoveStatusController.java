package com.company.networkmovers.modules.property.controller.admin;

import com.company.networkmovers.modules.property.service.MoveStatusService;
import com.company.networkmovers.shared.controller.AbstractLookupController;
import com.company.networkmovers.modules.property.dto.request.MoveStatusRequest;
import com.company.networkmovers.modules.property.dto.response.MoveStatusResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/move-status")
public class AdminMoveStatusController extends AbstractLookupController<MoveStatusRequest, MoveStatusResponse> {

    public AdminMoveStatusController(MoveStatusService service) {
        super(service);
    }
}
