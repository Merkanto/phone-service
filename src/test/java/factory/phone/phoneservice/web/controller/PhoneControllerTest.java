package factory.phone.phoneservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import factory.phone.phoneservice.bootstrap.PhoneLoader;
import factory.phone.phoneservice.services.PhoneService;
import factory.phone.phoneservice.web.model.PhoneDto;
import factory.phone.phoneservice.web.model.PhoneStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PhoneController.class)
class PhoneControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PhoneService phoneService;

    @Test
    void getPhoneById() throws Exception {

        given(phoneService.getById(any())).willReturn(getValidPhoneDto());

        mockMvc.perform(get("/api/v1/phone/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void saveNewPhone() throws Exception {

        PhoneDto phoneDto = getValidPhoneDto();
        String phoneDtoJson = objectMapper.writeValueAsString(phoneDto);

        given(phoneService.getById(any())).willReturn(getValidPhoneDto());

        mockMvc.perform(post("/api/v1/phone/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(phoneDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updatePhoneById() throws Exception {
        given(phoneService.getById(any())).willReturn(getValidPhoneDto());

        PhoneDto phoneDto = getValidPhoneDto();
        String phoneDtoJson = objectMapper.writeValueAsString(phoneDto);

        mockMvc.perform(put("/api/v1/phone/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(phoneDtoJson))
                .andExpect(status().isNoContent());
    }

    PhoneDto getValidPhoneDto() {
        return PhoneDto.builder()
                .phoneName("My Phone")
                .phoneStyle(PhoneStyleEnum.Monoblock)
                .price(new BigDecimal("9.99"))
                .imei(PhoneLoader.PHONE_1_IMEI)
                .build();
    }
}