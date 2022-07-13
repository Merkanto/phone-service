package factory.phone.phoneservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import factory.phone.phoneservice.web.model.PhoneDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PhoneController.class)
class PhoneControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getPhoneById() throws Exception {

        mockMvc.perform(get("/api/v1/phone/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void saveNewPhone() throws Exception {

        PhoneDto phoneDto = PhoneDto.builder().build();
        String phoneDtoJson = objectMapper.writeValueAsString(phoneDto);

        mockMvc.perform(post("/api/v1/phone/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(phoneDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updatePhoneById() throws Exception {
        PhoneDto phoneDto = PhoneDto.builder().build();
        String phoneDtoJson = objectMapper.writeValueAsString(phoneDto);

        mockMvc.perform(put("/api/v1/phone/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(phoneDtoJson))
                .andExpect(status().isNoContent());
    }
}