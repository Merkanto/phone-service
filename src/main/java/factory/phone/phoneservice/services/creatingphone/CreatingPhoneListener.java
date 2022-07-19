package factory.phone.phoneservice.services.creatingphone;

import factory.phone.phoneservice.config.JmsConfig;
import factory.phone.phoneservice.domain.Phone;
import factory.phone.phoneservice.events.CreatingPhoneEvent;
import factory.phone.phoneservice.events.NewInventoryEvent;
import factory.phone.phoneservice.repositories.PhoneRepository;
import factory.phone.phoneservice.web.model.PhoneDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreatingPhoneListener {

    private final PhoneRepository phoneRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = JmsConfig.CREATING_PHONE_REQUEST_QUEUE)
    public void listen(CreatingPhoneEvent event) {
        PhoneDto phoneDto = event.getPhoneDto();

        Phone phone = phoneRepository.getOne(phoneDto.getId());

        phoneDto.setQuantityOnHand(phone.getQuantityToCreate());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(phoneDto);

        log.debug("Creating beer " + phone.getMinOnHand() + " : QOH: " + phoneDto.getQuantityOnHand());

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }
}
