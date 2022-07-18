package factory.phone.phoneservice.services.inventory;

import factory.phone.phoneservice.bootstrap.PhoneLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled // utility for manual testing
@SpringBootTest
class PhoneInventoryServiceRestTemplateImplTest {

    @Autowired
    PhoneInventoryService phoneInventoryService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getOnhandInventory() {
        Integer qoh = phoneInventoryService.getOnhandInventory(PhoneLoader.PHONE_1_UUID);
        System.out.println(qoh);
    }
}