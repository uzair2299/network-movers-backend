package com.company.networkmovers.modules.wallet.service.impl;

import com.company.networkmovers.modules.wallet.entity.WalletEntity;
import com.company.networkmovers.modules.wallet.repository.WalletRepository;
import com.company.networkmovers.modules.wallet.service.WalletService;
import com.company.networkmovers.modules.wallet.dto.request.WalletRequest;
import com.company.networkmovers.modules.wallet.dto.response.WalletResponse;
import com.company.networkmovers.modules.wallet.mapper.WalletMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WalletServiceImpl implements WalletService {

    private final WalletRepository repository;
    private final WalletMapper mapper;

    public WalletServiceImpl(WalletRepository repository, WalletMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public WalletResponse create(WalletRequest request) {
        WalletEntity entity = mapper.toEntity(request);
        WalletEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public WalletResponse findById(Long id) {
        WalletEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WalletResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public WalletResponse update(Long id, WalletRequest request) {
        WalletEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        WalletEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        WalletEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet not found with id: " + id));
        repository.delete(entity);
    }
}
