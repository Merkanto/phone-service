package phoneinventoryservice.model.events;

import phoneinventoryservice.model.PhoneDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreatingPhoneEvent extends PhoneEvent {

    public CreatingPhoneEvent(PhoneDto phoneDto) {
        super(phoneDto);
    }
}
