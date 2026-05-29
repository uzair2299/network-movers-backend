package com.company.networkmovers.modules.property.controller.admin;

import com.company.networkmovers.modules.property.service.MovePhaseService;
import com.company.networkmovers.shared.controller.AbstractLookupController;
import com.company.networkmovers.modules.property.dto.request.MovePhaseRequest;
import com.company.networkmovers.modules.property.dto.response.MovePhaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/move-phase")
public class AdminMovePhaseController extends AbstractLookupController<MovePhaseRequest, MovePhaseResponse> {

    public AdminMovePhaseController(MovePhaseService service) {
        super(service);
    }
}
