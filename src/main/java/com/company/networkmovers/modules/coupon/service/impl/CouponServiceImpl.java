package com.company.networkmovers.modules.coupon.service.impl;

import com.company.networkmovers.modules.coupon.entity.CouponEntity;
import com.company.networkmovers.modules.coupon.repository.CouponRepository;
import com.company.networkmovers.modules.coupon.service.CouponService;
import com.company.networkmovers.modules.coupon.dto.request.CouponRequest;
import com.company.networkmovers.modules.coupon.dto.response.CouponResponse;
import com.company.networkmovers.modules.coupon.mapper.CouponMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CouponServiceImpl implements CouponService {

    private final CouponRepository repository;
    private final CouponMapper mapper;

    public CouponServiceImpl(CouponRepository repository, CouponMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CouponResponse create(CouponRequest request) {
        CouponEntity entity = mapper.toEntity(request);
        CouponEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CouponResponse findById(Long id) {
        CouponEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CouponResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CouponResponse update(Long id, CouponRequest request) {
        CouponEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        CouponEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        CouponEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found with id: " + id));
        repository.delete(entity);
    }
}
