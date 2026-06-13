package com.company.networkmovers.modules.property.controller.admin;

import com.company.networkmovers.modules.property.dto.request.MoveStatusWorkflowRequest;
import com.company.networkmovers.modules.property.dto.response.MoveStatusWorkflowResponse;
import com.company.networkmovers.modules.property.service.MoveStatusWorkflowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/move-status-transitions")
@Tag(name = "Admin Move Status Transitions", description = "Admin API for managing Move Status transitions and workflow layout")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminMoveStatusWorkflowController {

    private final MoveStatusWorkflowService workflowService;

    public AdminMoveStatusWorkflowController(MoveStatusWorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @PostMapping
    @Operation(
        summary = "Save Move Status Transitions Workflow",
        description = "Updates positions of status nodes and synchronizes all active transitions. Unspecified transitions are deleted."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Workflow saved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MoveStatusWorkflowResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request payload or status/role not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<MoveStatusWorkflowResponse> saveWorkflow(@RequestBody MoveStatusWorkflowRequest request) {
        return ResponseEntity.ok(workflowService.saveWorkflow(request));
    }

    @GetMapping
    @Operation(
        summary = "Get Move Status Transitions Workflow",
        description = "Retrieves positions of status nodes and all active transitions."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Workflow retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MoveStatusWorkflowResponse.class))
        ),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<MoveStatusWorkflowResponse> getWorkflow() {
        return ResponseEntity.ok(workflowService.getWorkflow());
    }
}
