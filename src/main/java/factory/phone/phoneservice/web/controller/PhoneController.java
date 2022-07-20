package factory.phone.phoneservice.web.controller;

import factory.phone.phoneservice.services.PhoneService;
import phoneinventoryservice.model.PhoneDto;
import phoneinventoryservice.model.PhonePagedList;
import phoneinventoryservice.model.PhoneStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class PhoneController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final PhoneService phoneService;

    @GetMapping(produces = { "application/json" }, path = "phone")
    public ResponseEntity<PhonePagedList> listPhones(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "phoneName", required = false) String phoneName,
                                                   @RequestParam(value = "phoneStyle", required = false) PhoneStyleEnum phoneStyle,
                                                   @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand){

        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        PhonePagedList phoneList = phoneService.listPhones(phoneName, phoneStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

        return new ResponseEntity<>(phoneList, HttpStatus.OK);
    }

    @GetMapping("phone/{phoneId}")
    public ResponseEntity<PhoneDto> getPhoneById(@PathVariable("phoneId") UUID phoneId,
                                                 @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand){
        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        return new ResponseEntity<>(phoneService.getById(phoneId, showInventoryOnHand), HttpStatus.OK);
    }

    @GetMapping("phoneImei/{imei}")
    public ResponseEntity<PhoneDto> getPhoneByImei(@PathVariable("imei") String imei){

        return new ResponseEntity<>(phoneService.getByImei(imei), HttpStatus.OK);
    }

    @PostMapping(path = "phone")
    public ResponseEntity saveNewPhone(@RequestBody @Validated PhoneDto phoneDto){
        return new ResponseEntity<>(phoneService.saveNewPhone(phoneDto), HttpStatus.CREATED);
    }

    @PutMapping("phone/{phoneId}")
    public ResponseEntity updatePhoneById(@PathVariable("phoneId") UUID phoneId, @RequestBody @Validated PhoneDto phoneDto){
        return new ResponseEntity<>(phoneService.updatePhone(phoneId, phoneDto), HttpStatus.NO_CONTENT);
    }

}