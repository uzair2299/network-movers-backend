package com.company.networkmovers.modules.document.controller.admin;

import com.company.networkmovers.modules.document.dto.request.DocumentTypeRequest;
import com.company.networkmovers.modules.document.dto.response.DocumentTypeResponse;
import com.company.networkmovers.modules.document.entity.DocumentType;
import com.company.networkmovers.modules.document.service.DocumentTypeService;
import com.company.networkmovers.shared.controller.AbstractLookupController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/document-types")
@Tag(name = "Admin Document Types", description = "Admin API for managing general Document Types")
public class AdminDocumentTypeController extends AbstractLookupController<DocumentTypeRequest, DocumentTypeResponse> {

    public AdminDocumentTypeController(DocumentTypeService service) {
        super(service);
    }
}
