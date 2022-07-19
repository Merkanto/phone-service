package factory.phone.phoneservice.events;

import factory.phone.phoneservice.web.model.PhoneDto;

public class CreatingPhoneEvent extends PhoneEvent {

    public CreatingPhoneEvent(PhoneDto phoneDto) {
        super(phoneDto);
    }
}
