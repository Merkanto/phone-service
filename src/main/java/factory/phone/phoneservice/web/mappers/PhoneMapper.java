package factory.phone.phoneservice.web.mappers;

import factory.phone.phoneservice.domain.Phone;
import phoneinventoryservice.model.PhoneDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = { DateMapper.class })
@DecoratedWith(PhoneMapperDecorator.class)
public interface PhoneMapper {

    PhoneDto phoneToPhoneDto(Phone phone);

    Phone phoneDtoToPhone(PhoneDto dto);

    PhoneDto phoneToPhoneDtoWithInventory(Phone phone);
}
