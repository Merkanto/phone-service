package factory.phone.phoneservice.bootstrap;

import factory.phone.phoneservice.domain.Phone;
import factory.phone.phoneservice.repositories.PhoneRepository;
import phoneinventoryservice.model.PhoneStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class PhoneLoader implements CommandLineRunner {

    public static final String PHONE_1_IMEI = "338694371652036";
    public static final String PHONE_2_IMEI = "334015010517204";
    public static final String PHONE_3_IMEI = "863571738204276";

    private final PhoneRepository phoneRepository;

    @Override
    public void run(String... args) throws Exception {
        if (phoneRepository.count() == 0) {
            loadPhoneObjects();
        }
    }

    private void loadPhoneObjects() {

        Phone p1 = Phone.builder()
                .phoneName("Samsung Galaxy Z")
                .phoneStyle(PhoneStyleEnum.FOLD.name())
                .minOnHand(12)
                .quantityToCreate(200)
                .price(new BigDecimal("1950"))
                .imei(PHONE_1_IMEI)
                .build();

        Phone p2 = Phone.builder()
                .phoneName("Huawei Pro 100")
                .phoneStyle(PhoneStyleEnum.FLIP.name())
                .minOnHand(12)
                .quantityToCreate(200)
                .price(new BigDecimal("1870"))
                .imei(PHONE_2_IMEI)
                .build();

        Phone p3 = Phone.builder()
                .phoneName("iPhone 13")
                .phoneStyle(PhoneStyleEnum.MONOBLOCK.name())
                .minOnHand(12)
                .quantityToCreate(200)
                .price(new BigDecimal("1870"))
                .imei(PHONE_3_IMEI)
                .build();

        phoneRepository.save(p1);
        phoneRepository.save(p2);
        phoneRepository.save(p3);
    }
}
