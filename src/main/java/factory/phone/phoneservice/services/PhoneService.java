package factory.phone.phoneservice.services;

import factory.phone.phoneservice.web.model.PhoneDto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface PhoneService {
    PhoneDto getById(UUID phoneId);

    PhoneDto saveNewPhone(PhoneDto phoneDto);

    PhoneDto updatePhone(UUID phoneId, PhoneDto phoneDto);
}
