package factory.phone.phoneservice.services.inventory;

import factory.phone.phoneservice.services.inventory.model.PhoneInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class InventoryServiceFeignClientFailover implements InventoryServiceFeignClient {

    private final InventoryFailoverFeignClient failoverFeignClient;

    @Override
    public ResponseEntity<List<PhoneInventoryDto>> getOnhandInventory(UUID phoneId) {
        return failoverFeignClient.getOnhandInventory();
    }
}
