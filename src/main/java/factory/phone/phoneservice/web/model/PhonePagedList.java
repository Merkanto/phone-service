package factory.phone.phoneservice.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PhonePagedList extends PageImpl<PhoneDto> {
    public PhonePagedList(List<PhoneDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public PhonePagedList(List<PhoneDto> content) {
        super(content);
    }
}
