package factory.phone.phoneservice.services;

import phoneinventoryservice.model.PhoneDto;
import phoneinventoryservice.model.PhonePagedList;
import phoneinventoryservice.model.PhoneStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface PhoneService {

    PhonePagedList listPhones(String phoneName, PhoneStyleEnum phoneStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    PhoneDto getById(UUID phoneId, Boolean showInventoryOnHand);

    PhoneDto saveNewPhone(PhoneDto phoneDto);

    PhoneDto updatePhone(UUID phoneId, PhoneDto phoneDto);

    PhoneDto getByImei(String imei);
}
