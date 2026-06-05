package com.company.networkmovers.modules.booking.service;

import com.company.networkmovers.modules.booking.dto.request.BookingRequest;
import com.company.networkmovers.modules.booking.dto.response.BookingResponse;
import com.company.networkmovers.modules.booking.entity.BookingEntity;
import com.company.networkmovers.modules.booking.mapper.BookingMapper;
import com.company.networkmovers.modules.booking.repository.BookingRepository;
import com.company.networkmovers.modules.booking.service.impl.BookingServiceImpl;
import com.company.networkmovers.modules.identity.entity.User;
import com.company.networkmovers.security.context.CustomUserDetails;
import com.company.networkmovers.modules.property.entity.MoveStatus;
import com.company.networkmovers.modules.property.repository.MoveStatusRepository;
import com.company.networkmovers.modules.booking.repository.BookingHistoryRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository repository;

    @Mock
    private BookingMapper mapper;

    @Mock
    private EntityManager entityManager;

    @Mock
    private MoveStatusRepository moveStatusRepository;

    @Mock
    private BookingHistoryRepository historyRepository;

    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        bookingService = new BookingServiceImpl(repository, mapper, entityManager, moveStatusRepository, historyRepository);
    }

    @Test
    void testFindById_Success() {
        // Arrange
        UUID bookingId = UUID.randomUUID();
        BookingEntity entity = BookingEntity.builder()
                .id(bookingId)
                .pickupAddress("Pickup Address")
                .destinationAddress("Destination Address")
                .build();
        BookingResponse response = BookingResponse.builder()
                .id(bookingId)
                .routeDetails(BookingResponse.RouteDetailsResponse.builder()
                        .pickupAddress("Pickup Address")
                        .destinationAddress("Destination Address")
                        .build())
                .build();

        when(repository.findByIdWithDetails(bookingId)).thenReturn(Optional.of(entity));
        when(mapper.toResponse(entity)).thenReturn(response);

        // Act
        BookingResponse result = bookingService.findById(bookingId);

        // Assert
        assertNotNull(result);
        assertEquals(bookingId, result.getId());
        assertEquals("Pickup Address", result.getRouteDetails().getPickupAddress());
        verify(repository, times(1)).findByIdWithDetails(bookingId);
    }

    @Test
    void testCreate_WithoutUser_Success() {
        // Arrange
        BookingRequest request = BookingRequest.builder()
                .routeDetails(BookingRequest.RouteDetails.builder()
                        .pickupAddress("Pickup Address")
                        .destinationAddress("Destination Address")
                        .build())
                .build();
        BookingEntity mappedEntity = BookingEntity.builder()
                .pickupAddress("Pickup Address")
                .destinationAddress("Destination Address")
                .build();
        UUID generatedId = UUID.randomUUID();
        BookingEntity savedEntity = BookingEntity.builder()
                .id(generatedId)
                .pickupAddress("Pickup Address")
                .destinationAddress("Destination Address")
                .build();
        BookingResponse response = BookingResponse.builder()
                .id(generatedId)
                .routeDetails(BookingResponse.RouteDetailsResponse.builder()
                        .pickupAddress("Pickup Address")
                        .destinationAddress("Destination Address")
                        .build())
                .build();

        MoveStatus defaultStatus = MoveStatus.builder()
                .code("REQUESTED")
                .name("Requested")
                .build();
        when(moveStatusRepository.findByCode("REQUESTED")).thenReturn(Optional.of(defaultStatus));

        when(mapper.toEntity(request)).thenReturn(mappedEntity);
        when(repository.save(mappedEntity)).thenReturn(savedEntity);
        when(repository.findByIdWithDetails(generatedId)).thenReturn(Optional.of(savedEntity));
        when(mapper.toResponse(savedEntity)).thenReturn(response);

        // Act
        BookingResponse result = bookingService.create(request);

        // Assert
        assertNotNull(result);
        assertEquals(generatedId, result.getId());
        verify(repository, times(1)).save(mappedEntity);
        verify(entityManager, times(1)).flush();
        verify(repository, times(1)).findByIdWithDetails(generatedId);
    }

    @Test
    void testCreate_WithAuthenticatedUser_Success() {
        // Arrange
        BookingRequest request = BookingRequest.builder()
                .routeDetails(BookingRequest.RouteDetails.builder()
                        .pickupAddress("Pickup Address")
                        .destinationAddress("Destination Address")
                        .build())
                .build();
        BookingEntity mappedEntity = spy(BookingEntity.builder()
                .pickupAddress("Pickup Address")
                .destinationAddress("Destination Address")
                .build());
        UUID generatedId = UUID.randomUUID();
        BookingEntity savedEntity = BookingEntity.builder()
                .id(generatedId)
                .pickupAddress("Pickup Address")
                .destinationAddress("Destination Address")
                .build();
        UUID userId = UUID.randomUUID();
        BookingResponse response = BookingResponse.builder()
                .id(generatedId)
                .user(BookingResponse.UserDetailsResponse.builder().id(userId).build())
                .build();

        // Mock authentication context
        Authentication auth = mock(Authentication.class);
        CustomUserDetails userDetails = new CustomUserDetails(userId, "testuser", "pass", true, Collections.emptyList());

        when(auth.isAuthenticated()).thenReturn(true);
        when(auth.getPrincipal()).thenReturn(userDetails);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(auth);

        SecurityContextHolder.setContext(securityContext);

        try {
            User userProxy = mock(User.class);
            when(entityManager.getReference(User.class, userId)).thenReturn(userProxy);

            MoveStatus defaultStatus = MoveStatus.builder()
                    .code("REQUESTED")
                    .name("Requested")
                    .build();
            when(moveStatusRepository.findByCode("REQUESTED")).thenReturn(Optional.of(defaultStatus));

            when(mapper.toEntity(request)).thenReturn(mappedEntity);
            when(repository.save(mappedEntity)).thenReturn(savedEntity);
            when(repository.findByIdWithDetails(generatedId)).thenReturn(Optional.of(savedEntity));
            when(mapper.toResponse(savedEntity)).thenReturn(response);

            // Act
            BookingResponse result = bookingService.create(request);

            // Assert
            assertNotNull(result);
            assertEquals(generatedId, result.getId());
            assertEquals(userId, result.getUser().getId());
            verify(mappedEntity).setUser(userProxy);
            verify(repository, times(1)).save(mappedEntity);
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    @Test
    void testGetAllByUserId_Success() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UUID bookingId = UUID.randomUUID();
        BookingEntity entity = BookingEntity.builder()
                .id(bookingId)
                .pickupAddress("Pickup Address")
                .build();
        BookingResponse response = BookingResponse.builder()
                .id(bookingId)
                .user(BookingResponse.UserDetailsResponse.builder().id(userId).build())
                .build();

        com.company.networkmovers.shared.dto.RequestParamDto requestParams = new com.company.networkmovers.shared.dto.RequestParamDto();
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(0, 20, org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.ASC, "name"));
        org.springframework.data.domain.Page<BookingEntity> entityPage = new org.springframework.data.domain.PageImpl<>(java.util.Collections.singletonList(entity), pageable, 1);
        
        when(repository.findAllByUserIdWithDetails(eq(userId), any(org.springframework.data.domain.Pageable.class))).thenReturn(entityPage);
        when(mapper.toResponse(entity)).thenReturn(response);

        // Act
        org.springframework.data.domain.Page<BookingResponse> result = bookingService.getAllByUserId(userId, requestParams);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(bookingId, result.getContent().get(0).getId());
        assertEquals(userId, result.getContent().get(0).getUser().getId());
        verify(repository, times(1)).findAllByUserIdWithDetails(eq(userId), any(org.springframework.data.domain.Pageable.class));
    }

    @Test
    void testFindByIdAndUserId_Success() {
        // Arrange
        UUID bookingId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        BookingEntity entity = BookingEntity.builder()
                .id(bookingId)
                .build();
        BookingResponse response = BookingResponse.builder()
                .id(bookingId)
                .user(BookingResponse.UserDetailsResponse.builder().id(userId).build())
                .build();

        when(repository.findByIdAndUserIdWithDetails(bookingId, userId)).thenReturn(Optional.of(entity));
        when(mapper.toResponse(entity)).thenReturn(response);

        // Act
        BookingResponse result = bookingService.findByIdAndUserId(bookingId, userId);

        // Assert
        assertNotNull(result);
        assertEquals(bookingId, result.getId());
        assertEquals(userId, result.getUser().getId());
        verify(repository, times(1)).findByIdAndUserIdWithDetails(bookingId, userId);
    }

    @Test
    void testFindByIdAndUserId_NotFound() {
        // Arrange
        UUID bookingId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        when(repository.findByIdAndUserIdWithDetails(bookingId, userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> bookingService.findByIdAndUserId(bookingId, userId));
        verify(repository, times(1)).findByIdAndUserIdWithDetails(bookingId, userId);
    }
}
