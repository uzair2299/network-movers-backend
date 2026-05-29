package com.company.networkmovers.modules.booking.dto.response;

import com.company.networkmovers.modules.property.dto.response.MoveStatusResponse;
import com.company.networkmovers.modules.booking.entity.ScheduleType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {
    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    private String name;
    private String description;

    @JsonProperty("current_status")
    private MoveStatusResponse currentStatus;

    @JsonProperty("route_details")
    private RouteDetailsResponse routeDetails;

    private SchedulingResponse scheduling;

    @JsonProperty("move_specifications")
    private MoveSpecificationsResponse moveSpecifications;

    @JsonProperty("access_details")
    private AccessDetailsResponse accessDetails;

    private LocalDateTime createdAt;
    private Long createdBy;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RouteDetailsResponse {
        @JsonProperty("pickup_address")
        private String pickupAddress;

        @JsonProperty("pickup_latitude")
        private Double pickupLatitude;

        @JsonProperty("pickup_longitude")
        private Double pickupLongitude;

        @JsonProperty("destination_address")
        private String destinationAddress;

        @JsonProperty("destination_latitude")
        private Double destinationLatitude;

        @JsonProperty("destination_longitude")
        private Double destinationLongitude;

        @JsonProperty("distance_km")
        private Double distanceKm;

        @JsonProperty("duration_minutes")
        private Integer durationMinutes;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SchedulingResponse {
        @JsonProperty("schedule_type")
        private ScheduleType scheduleType;

        @JsonProperty("scheduled_date")
        private OffsetDateTime scheduledDate;

        @JsonProperty("time_slot")
        private String timeSlot;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MoveSpecificationsResponse {
        @JsonProperty("property_category_id")
        private UUID propertyCategoryId;

        @JsonProperty("property_category_code")
        private String propertyCategoryCode;

        @JsonProperty("property_category_name")
        private String propertyCategoryName;

        @JsonProperty("property_type_id")
        private UUID propertyTypeId;

        @JsonProperty("property_type_code")
        private String propertyTypeCode;

        @JsonProperty("property_type_name")
        private String propertyTypeName;

        @JsonProperty("property_size_id")
        private UUID propertySizeId;

        @JsonProperty("property_size_code")
        private String propertySizeCode;

        @JsonProperty("property_size_name")
        private String propertySizeName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AccessDetailsResponse {
        private AccessPointResponse pickup;
        private AccessPointResponse destination;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class AccessPointResponse {
            @JsonProperty("floor_type_id")
            private UUID floorTypeId;

            @JsonProperty("floor_type_code")
            private String floorTypeCode;

            @JsonProperty("floor_type_name")
            private String floorTypeName;

            @JsonProperty("building_access_id")
            private UUID buildingAccessId;

            @JsonProperty("building_access_code")
            private String buildingAccessCode;

            @JsonProperty("building_access_name")
            private String buildingAccessName;

            @JsonProperty("parking_access_id")
            private UUID parkingAccessId;

            @JsonProperty("parking_access_code")
            private String parkingAccessCode;

            @JsonProperty("parking_access_name")
            private String parkingAccessName;

            @JsonProperty("restriction_details")
            private List<RestrictionResponse> restrictionDetails;
        }

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class RestrictionResponse {
            private UUID id;
            private String code;
            private String name;
        }
    }
}
