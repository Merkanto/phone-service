package factory.phone.phoneservice.events;

import factory.phone.phoneservice.web.model.PhoneDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class PhoneEvent implements Serializable {

    static final long serialVersionUID = 5583029845770262338L;

    private final PhoneDto phoneDto;
}
