package com.company.networkmovers.modules.rbac.controller.admin;

import com.company.networkmovers.modules.rbac.dto.request.ResourceRequest;
import com.company.networkmovers.modules.rbac.dto.response.ResourceResponse;
import com.company.networkmovers.modules.rbac.service.ResourceService;
import com.company.networkmovers.shared.controller.AbstractLookupController;
import com.company.networkmovers.shared.dto.RequestParamDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("modulesAdminResourceController")
@RequestMapping("/api/v1/admin/rbac/resources")
@Tag(name = "Admin Resources", description = "Admin API for managing Resources")
public class AdminResourceController extends AbstractLookupController<ResourceRequest, ResourceResponse> {

    private final ResourceService resourceService;

    public AdminResourceController(@Qualifier("modulesResourceServiceImpl") ResourceService service) {
        super(service);
        this.resourceService = service;
    }

    @Override
    @PostMapping
    @Operation(summary = "Create a new Resource", description = "Creates a new system resource. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Resource created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceResponse.class))),
        @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<ResourceResponse> create(@RequestBody ResourceRequest request) {
        return new ResponseEntity<>(resourceService.create(request), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Resource", description = "Updates fields of an existing resource by ID. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Resource updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceResponse.class))),
        @ApiResponse(responseCode = "400", description = "Validation failed or resource not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<ResourceResponse> update(
            @Parameter(description = "ID of the resource to update", required = true) @PathVariable UUID id,
            @RequestBody ResourceRequest request) {
        return ResponseEntity.ok(resourceService.update(id, request));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Get Resource by ID", description = "Retrieves details of a single resource by its unique ID. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Resource details returned successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResourceResponse.class))),
        @ApiResponse(responseCode = "400", description = "Resource not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<ResourceResponse> getById(
            @Parameter(description = "ID of the resource to retrieve", required = true) @PathVariable UUID id) {
        return ResponseEntity.ok(resourceService.getById(id));
    }

    @Override
    @GetMapping
    @Operation(summary = "Search Resources with pagination", description = "Returns a paginated list of resources matching optional search filter. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Paginated list of resources returned successfully"),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<Page<ResourceResponse>> getAll(
            @org.springdoc.core.annotations.ParameterObject RequestParamDto requestParams) {
        return ResponseEntity.ok(resourceService.getAll(requestParams));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Resource", description = "Soft-deletes (deactivates) a resource by its ID. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Resource deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Resource not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the resource to delete", required = true) @PathVariable UUID id) {
        resourceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
