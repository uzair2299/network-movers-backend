package com.company.networkmovers.modules.rbac.controller.admin;

import com.company.networkmovers.modules.rbac.dto.request.ModuleRequest;
import com.company.networkmovers.modules.rbac.dto.response.ModuleResponse;
import com.company.networkmovers.modules.rbac.service.ModuleService;
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

@RestController("modulesAdminModuleController")
@RequestMapping("/api/v1/admin/rbac/modules")
@Tag(name = "Admin Modules", description = "Admin API for managing System Modules")
public class AdminModuleController extends AbstractLookupController<ModuleRequest, ModuleResponse> {

    private final ModuleService moduleService;

    public AdminModuleController(@Qualifier("modulesModuleServiceImpl") ModuleService service) {
        super(service);
        this.moduleService = service;
    }

    @Override
    @PostMapping
    @Operation(summary = "Create a new Module", description = "Creates a new system module. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Module created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModuleResponse.class))),
        @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<ModuleResponse> create(@RequestBody ModuleRequest request) {
        return new ResponseEntity<>(moduleService.create(request), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Module", description = "Updates fields of an existing module by ID. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Module updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModuleResponse.class))),
        @ApiResponse(responseCode = "400", description = "Validation failed or module not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<ModuleResponse> update(
            @Parameter(description = "ID of the module to update", required = true) @PathVariable UUID id,
            @RequestBody ModuleRequest request) {
        return ResponseEntity.ok(moduleService.update(id, request));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Get Module by ID", description = "Retrieves details of a single module by its unique ID. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Module details returned successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModuleResponse.class))),
        @ApiResponse(responseCode = "400", description = "Module not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<ModuleResponse> getById(
            @Parameter(description = "ID of the module to retrieve", required = true) @PathVariable UUID id) {
        return ResponseEntity.ok(moduleService.getById(id));
    }

    @Override
    @GetMapping
    @Operation(summary = "Search Modules with pagination", description = "Returns a paginated list of modules matching optional search filter. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Paginated list of modules returned successfully"),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<Page<ModuleResponse>> getAll(
            @org.springdoc.core.annotations.ParameterObject RequestParamDto requestParams) {
        return ResponseEntity.ok(moduleService.getAll(requestParams));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Module", description = "Soft-deletes (deactivates) a module by its ID. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Module deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Module not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the module to delete", required = true) @PathVariable UUID id) {
        moduleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
