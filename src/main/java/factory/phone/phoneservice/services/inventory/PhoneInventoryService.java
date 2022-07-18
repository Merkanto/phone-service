package factory.phone.phoneservice.services.inventory;

import java.util.UUID;

public interface PhoneInventoryService {

    Integer getOnhandInventory(UUID phoneId);
}
