package factory.phone.phoneservice.services;

import factory.phone.phoneservice.domain.Phone;
import factory.phone.phoneservice.repositories.PhoneRepository;
import factory.phone.phoneservice.web.controller.NotFoundException;
import factory.phone.phoneservice.web.mappers.PhoneMapper;
import factory.phone.phoneservice.web.model.PhoneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;
    private final PhoneMapper phoneMapper;

    @Override
    public PhoneDto getById(UUID phoneId) {
        return phoneMapper.phoneToPhoneDto(phoneRepository.findById(phoneId).orElseThrow(NotFoundException::new));
    }

    @Override
    public PhoneDto saveNewPhone(PhoneDto phoneDto) {
        return phoneMapper.phoneToPhoneDto(phoneRepository.save(phoneMapper.phoneDtoToPhone(phoneDto)));
    }

    @Override
    public PhoneDto updatePhone(UUID phoneId, PhoneDto phoneDto) {
        Phone phone = phoneRepository.findById(phoneId).orElseThrow(NotFoundException::new);

        phone.setPhoneName(phoneDto.getPhoneName());
        phone.setPhoneStyle(phoneDto.getPhoneStyle().name());
        phone.setPrice(phoneDto.getPrice());
        phone.setImei(phoneDto.getImei());

        return phoneMapper.phoneToPhoneDto(phoneRepository.save(phone));
    }
}
