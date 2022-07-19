package factory.phone.phoneservice.repositories;

import factory.phone.phoneservice.domain.Phone;
import factory.phone.phoneservice.web.model.PhoneStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneRepository extends JpaRepository<Phone, UUID> {
    Page<Phone> findAllByPhoneName(String phoneName, Pageable pageable);

    Page<Phone> findAllByPhoneStyle(PhoneStyleEnum phoneStyle, Pageable pageable);

    Page<Phone> findAllByPhoneNameAndPhoneStyle(String phoneName, PhoneStyleEnum phoneStyle, Pageable pageable);

    Phone findByImei(String imei);
}
