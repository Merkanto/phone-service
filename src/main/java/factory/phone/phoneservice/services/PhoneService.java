package factory.phone.phoneservice.services;

import factory.phone.phoneservice.web.model.PhoneDto;
import factory.phone.phoneservice.web.model.PhonePagedList;
import factory.phone.phoneservice.web.model.PhoneStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface PhoneService {

    PhonePagedList listPhones(String phoneName, PhoneStyleEnum phoneStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    PhoneDto getById(UUID phoneId, Boolean showInventoryOnHand);

    PhoneDto saveNewPhone(PhoneDto phoneDto);

    PhoneDto updatePhone(UUID phoneId, PhoneDto phoneDto);
}
