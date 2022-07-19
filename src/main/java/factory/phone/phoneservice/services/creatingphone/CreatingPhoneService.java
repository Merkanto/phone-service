package factory.phone.phoneservice.services.creatingphone;

import factory.phone.phoneservice.config.JmsConfig;
import factory.phone.phoneservice.domain.Phone;
import factory.phone.phoneservice.events.CreatingPhoneEvent;
import factory.phone.phoneservice.repositories.PhoneRepository;
import factory.phone.phoneservice.services.inventory.PhoneInventoryService;
import factory.phone.phoneservice.web.mappers.PhoneMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreatingPhoneService {

    private final PhoneRepository phoneRepository;
    private final PhoneInventoryService phoneInventoryService;
    private final JmsTemplate jmsTemplate;
    private final PhoneMapper phoneMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {
        List<Phone> phones = phoneRepository.findAll();

        phones.forEach(phone -> {
            Integer invQOH = phoneInventoryService.getOnhandInventory(phone.getId());
            log.debug("Min Onhand is: " + phone.getMinOnHand());
            log.debug("Inventory is: " + invQOH);

            if (phone.getMinOnHand() >= invQOH) {
                jmsTemplate.convertAndSend(JmsConfig.CREATING_PHONE_REQUEST_QUEUE, new CreatingPhoneEvent(phoneMapper.phoneToPhoneDto(phone)));
            }
        });
    }
}
