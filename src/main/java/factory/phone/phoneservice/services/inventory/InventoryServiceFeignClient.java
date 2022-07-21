package factory.phone.phoneservice.services.inventory;

import factory.phone.phoneservice.services.inventory.model.PhoneInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "inventory-service")
public interface InventoryServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = PhoneInventoryServiceRestTemplateImpl.INVENTORY_PATH)
    ResponseEntity<List<PhoneInventoryDto>> getOnhandInventory(@PathVariable UUID phoneId);
}
