package factory.phone.phoneservice.services;

import factory.phone.phoneservice.domain.Phone;
import factory.phone.phoneservice.repositories.PhoneRepository;
import factory.phone.phoneservice.web.controller.NotFoundException;
import factory.phone.phoneservice.web.mappers.PhoneMapper;
import factory.phone.phoneservice.web.model.PhoneDto;
import factory.phone.phoneservice.web.model.PhonePagedList;
import factory.phone.phoneservice.web.model.PhoneStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;
    private final PhoneMapper phoneMapper;

    @Cacheable(cacheNames = "phoneListCache", condition = "#showInventoryOnHand == false ")
    @Override
    public PhonePagedList listPhones(String phoneName, PhoneStyleEnum phoneStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {

        PhonePagedList phonePagedList;
        Page<Phone> phonePage;

        if (!StringUtils.isEmpty(phoneName) && !StringUtils.isEmpty(phoneStyle)) {
            //search both
            phonePage = phoneRepository.findAllByPhoneNameAndPhoneStyle(phoneName, phoneStyle, pageRequest);
        } else if (!StringUtils.isEmpty(phoneName) && StringUtils.isEmpty(phoneStyle)) {
            //search phone_service name
            phonePage = phoneRepository.findAllByPhoneName(phoneName, pageRequest);
        } else if (StringUtils.isEmpty(phoneName) && !StringUtils.isEmpty(phoneStyle)) {
            //search phone_service style
            phonePage = phoneRepository.findAllByPhoneStyle(phoneStyle, pageRequest);
        } else {
            phonePage = phoneRepository.findAll(pageRequest);
        }

        if (showInventoryOnHand){
            phonePagedList = new PhonePagedList(phonePage
                    .getContent()
                    .stream()
                    .map(phoneMapper::phoneToPhoneDtoWithInventory)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(phonePage.getPageable().getPageNumber(),
                                    phonePage.getPageable().getPageSize()),
                    phonePage.getTotalElements());
        } else {
            phonePagedList = new PhonePagedList(phonePage
                    .getContent()
                    .stream()
                    .map(phoneMapper::phoneToPhoneDto)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(phonePage.getPageable().getPageNumber(),
                                    phonePage.getPageable().getPageSize()),
                    phonePage.getTotalElements());
        }

        return phonePagedList;
    }

    @Cacheable(cacheNames = "phoneCache", key = "#phoneId", condition = "#showInventoryOnHand == false ")
    @Override
    public PhoneDto getById(UUID phoneId, Boolean showInventoryOnHand) {
        if (showInventoryOnHand) {
            return phoneMapper.phoneToPhoneDtoWithInventory(
                    phoneRepository.findById(phoneId).orElseThrow(NotFoundException::new)
            );
        } else {
            return phoneMapper.phoneToPhoneDto(
                    phoneRepository.findById(phoneId).orElseThrow(NotFoundException::new)
            );
        }
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

    @Cacheable(cacheNames = "phoneImeiCache")
    @Override
    public PhoneDto getByImei(String imei) {
        return phoneMapper.phoneToPhoneDto(phoneRepository.findByImei(imei));
    }
}
