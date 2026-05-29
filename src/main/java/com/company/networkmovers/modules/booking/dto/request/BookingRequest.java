package com.company.networkmovers.modules.booking.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequest {

    private String name;
    private String description;

    @JsonProperty("current_status_id")
    private UUID currentStatusId;

    @JsonProperty("route_details")
    private RouteDetails routeDetails;

    private Scheduling scheduling;

    @JsonProperty("move_specifications")
    private MoveSpecifications moveSpecifications;

    @JsonProperty("access_details")
    private AccessDetails accessDetails;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RouteDetails {
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
    public static class Scheduling {
        @JsonProperty("schedule_type")
        private String scheduleType;

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
    public static class MoveSpecifications {
        @JsonProperty("property_category_id")
        private UUID propertyCategoryId;

        @JsonProperty("property_category_code")
        private String propertyCategoryCode;

        @JsonProperty("property_type_id")
        private UUID propertyTypeId;

        @JsonProperty("property_type_code")
        private String propertyTypeCode;

        @JsonProperty("property_size_id")
        private UUID propertySizeId;

        @JsonProperty("property_size_code")
        private String propertySizeCode;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AccessDetails {
        private AccessPoint pickup;
        private AccessPoint destination;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class AccessPoint {
            @JsonProperty("floor_type_id")
            private UUID floorTypeId;

            @JsonProperty("floor_type_code")
            private String floorTypeCode;

            @JsonProperty("building_access_id")
            private UUID buildingAccessId;

            @JsonProperty("building_access_code")
            private String buildingAccessCode;

            @JsonProperty("parking_access_id")
            private UUID parkingAccessId;

            @JsonProperty("parking_access_code")
            private String parkingAccessCode;

            @JsonProperty("restriction_ids")
            private List<UUID> restrictionIds;
        }
    }
}
