package factory.phone.phoneservice.web.controller;

import factory.phone.phoneservice.web.model.PhoneDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/phone")
@RestController
public class PhoneController {

    @GetMapping("/{phoneId}")
    public ResponseEntity<PhoneDto> getPhoneById(@PathVariable UUID phoneId) {

        return new ResponseEntity<>(PhoneDto.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewPhone(@RequestBody PhoneDto phoneDto) {

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{phoneId}")
    public ResponseEntity updatePhoneById(@PathVariable("phoneId") UUID phoneId, @RequestBody PhoneDto phoneDto) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
