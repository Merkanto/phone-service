package factory.phone.phoneservice.web.mappers;

import factory.phone.phoneservice.domain.Phone;
import factory.phone.phoneservice.services.inventory.PhoneInventoryService;
import factory.phone.phoneservice.web.model.PhoneDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PhoneMapperDecorator implements PhoneMapper {

    private PhoneInventoryService phoneInventoryService;
    private PhoneMapper mapper;

    @Autowired
    public void setPhoneInventoryService(PhoneInventoryService phoneInventoryService) {
        this.phoneInventoryService = phoneInventoryService;
    }

    @Autowired
    public void setMapper(PhoneMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public PhoneDto phoneToPhoneDto(Phone phone) {
        return mapper.phoneToPhoneDto(phone);
    }

    @Override
    public PhoneDto phoneToPhoneDtoWithInventory(Phone phone) {
        PhoneDto dto = mapper.phoneToPhoneDto(phone);
        dto.setQuantityOnHand(phoneInventoryService.getOnhandInventory(phone.getId()));
        return dto;
    }

    @Override
    public Phone phoneDtoToPhone(PhoneDto phoneDto) {
        return mapper.phoneDtoToPhone(phoneDto);
    }
}
