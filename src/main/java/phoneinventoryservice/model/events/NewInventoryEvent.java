package phoneinventoryservice.model.events;

import phoneinventoryservice.model.PhoneDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NewInventoryEvent extends PhoneEvent {

    public NewInventoryEvent(PhoneDto phoneDto) {
        super(phoneDto);
    }
}
