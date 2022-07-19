package factory.phone.phoneservice.events;

import factory.phone.phoneservice.web.model.PhoneDto;

public class NewInventoryEvent extends PhoneEvent {

    public NewInventoryEvent(PhoneDto phoneDto) {
        super(phoneDto);
    }
}
