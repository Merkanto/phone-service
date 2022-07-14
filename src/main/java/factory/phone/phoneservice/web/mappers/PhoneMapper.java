package factory.phone.phoneservice.web.mappers;

import factory.phone.phoneservice.domain.Phone;
import factory.phone.phoneservice.web.model.PhoneDto;
import org.mapstruct.Mapper;

@Mapper(uses = { DateMapper.class })
public interface PhoneMapper {

    PhoneDto phoneToPhoneDto(Phone phone);

    Phone phoneDtoToPhone(PhoneDto dto);
}
