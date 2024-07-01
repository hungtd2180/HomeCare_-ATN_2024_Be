package com.example.homecare.service;

import com.example.homecare.model.dto.PriceObject;
import com.example.homecare.model.entity.Collaborator;
import com.example.homecare.model.entity.Price;
import com.example.homecare.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.homecare.util.EncoderUtil.createId;

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private CollaboratorService collaboratorService;
    public List<Price> getPriceListByCollaboratorId(String collaboratorId) {
        return priceRepository.findPricesByCollaborator_CollaboratorIdOrderByType(collaboratorId);
    }
    public Optional<Price> getById(String collaboratorId) {
        return priceRepository.findById(collaboratorId);
    }
    public Price savePrice(PriceObject priceObject) {
        Price price = new Price();
        if (priceObject.getPriceId() == null ||priceObject.getPriceId().isEmpty()) {
            price.setPriceId(createId("price"));
        }
        price.setType(priceObject.getType());
        price.setName(priceObject.getName());
        price.setPriceLow(priceObject.getPriceLow());
        price.setPriceHigh(priceObject.getPriceHigh());
        Optional<Collaborator> collaborator = collaboratorService.getCollaboratorById(priceObject.getCollaboratorId());
        price.setCollaborator(collaborator.get());
        return priceRepository.save(price);
    }
    public Price update(Price price) {
        return priceRepository.save(price);
    }

    public void deletePrice(String id) {
        priceRepository.deleteById(id);
    }
}
