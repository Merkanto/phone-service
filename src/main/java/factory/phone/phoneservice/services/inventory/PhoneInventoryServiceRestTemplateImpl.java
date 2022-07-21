package factory.phone.phoneservice.services.inventory;

import factory.phone.phoneservice.services.inventory.model.PhoneInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Profile("!local-discovery")
@Slf4j
@ConfigurationProperties(prefix = "phoneinventoryservice", ignoreUnknownFields = true)
@Component
public class PhoneInventoryServiceRestTemplateImpl implements PhoneInventoryService {

    public static final String INVENTORY_PATH = "/api/v1/phone/{phoneId}/inventory";
    private final RestTemplate restTemplate;

    private String phoneInventoryServiceHost;

    public void setPhoneInventoryServiceHost(String phoneInventoryServiceHost) {
        this.phoneInventoryServiceHost = phoneInventoryServiceHost;
    }

    public PhoneInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Integer getOnhandInventory(UUID phoneId) {

        log.debug("Calling Inventory Service");

        ResponseEntity<List<PhoneInventoryDto>> responseEntity = restTemplate
                .exchange(phoneInventoryServiceHost + INVENTORY_PATH, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<PhoneInventoryDto>>(){}, (Object) phoneId);

        //sum from inventory list
        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(PhoneInventoryDto::getQuantityOnHand)
                .sum();

        return onHand;
    }
}
