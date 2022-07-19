package factory.phone.phoneservice.events;

import factory.phone.phoneservice.web.model.PhoneDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreatingPhoneEvent extends PhoneEvent {

    public CreatingPhoneEvent(PhoneDto phoneDto) {
        super(phoneDto);
    }
}
