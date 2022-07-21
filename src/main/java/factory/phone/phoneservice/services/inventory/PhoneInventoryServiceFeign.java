package factory.phone.phoneservice.services.inventory;

import factory.phone.phoneservice.services.inventory.model.PhoneInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Profile("local-discovery")
@Service
public class PhoneInventoryServiceFeign implements PhoneInventoryService{

    private final InventoryServiceFeignClient inventoryServiceFeignClient;

    @Override
    public Integer getOnhandInventory(UUID phoneId) {
        log.debug("Calling Inventory Service - PhoneId: " + phoneId);

        ResponseEntity<List<PhoneInventoryDto>> responseEntity = inventoryServiceFeignClient.getOnhandInventory(phoneId);

        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(PhoneInventoryDto::getQuantityOnHand)
                .sum();

        log.debug("PhoneId: " + phoneId + " On hand is: " + onHand);

        return onHand;
    }
}
