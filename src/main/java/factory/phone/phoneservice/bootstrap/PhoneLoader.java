package factory.phone.phoneservice.bootstrap;

import factory.phone.phoneservice.domain.Phone;
import factory.phone.phoneservice.repositories.PhoneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PhoneLoader implements CommandLineRunner {

    public static final String PHONE_1_IMEI = "338694371652036";
    public static final String PHONE_2_IMEI = "334015010517204";
    public static final String PHONE_3_IMEI = "863571738204276";

    private final PhoneRepository phoneRepository;

    public PhoneLoader(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadPhoneObjects();
    }

    private void loadPhoneObjects() {
        if (phoneRepository.count() == 0) {

            phoneRepository.save(Phone.builder()
                    .phoneName("Samsung Galaxy Z")
                    .phoneStyle("Fold")
                    .quantityToCreate(200)
                    .minOnHand(12)
                    .imei(PHONE_1_IMEI)
                    .price(new BigDecimal("1950"))
                    .build());

            phoneRepository.save(Phone.builder()
                    .phoneName("Huawei Pro 100")
                    .phoneStyle("Flip")
                    .quantityToCreate(200)
                    .minOnHand(12)
                    .imei(PHONE_2_IMEI)
                    .price(new BigDecimal("1870"))
                    .build());

            phoneRepository.save(Phone.builder()
                    .phoneName("iPhone 13")
                    .phoneStyle("MONOBLOCK")
                    .quantityToCreate(200)
                    .minOnHand(12)
                    .imei(PHONE_3_IMEI)
                    .price(new BigDecimal("1870"))
                    .build());
        }

        System.out.println("Loaded phones: " + phoneRepository.count());
    }
}
