package factory.phone.phoneservice.web.controller;

import factory.phone.phoneservice.services.PhoneService;
import factory.phone.phoneservice.web.model.PhoneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/phone")
@RestController
public class PhoneController {

    private final PhoneService phoneService;

    @GetMapping("/{phoneId}")
    public ResponseEntity<PhoneDto> getPhoneById(@PathVariable UUID phoneId) {

        return new ResponseEntity<>(phoneService.getById(phoneId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewPhone(@RequestBody @Validated PhoneDto phoneDto) {

        return new ResponseEntity<>(phoneService.saveNewPhone(phoneDto), HttpStatus.CREATED);
    }

    @PutMapping("/{phoneId}")
    public ResponseEntity updatePhoneById(@PathVariable("phoneId") UUID phoneId, @RequestBody @Validated PhoneDto phoneDto) {
        return new ResponseEntity<>(phoneService.updatePhone(phoneId, phoneDto), HttpStatus.NO_CONTENT);
    }
}
