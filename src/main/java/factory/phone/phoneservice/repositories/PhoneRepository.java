package factory.phone.phoneservice.repositories;

import factory.phone.phoneservice.domain.Phone;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface PhoneRepository extends PagingAndSortingRepository<Phone, UUID> {
}
