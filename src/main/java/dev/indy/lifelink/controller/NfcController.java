package dev.indy.lifelink.controller;

import dev.indy.lifelink.exception.HttpException;
import dev.indy.lifelink.exception.InvalidAuthenticationCredentialsException;
import dev.indy.lifelink.model.request.RegisterNfcTagRequest;
import dev.indy.lifelink.model.response.MessageResponse;
import dev.indy.lifelink.service.NfcService;
import dev.indy.lifelink.util.Util;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nfc")
public class NfcController {
    private final NfcService _nfcService;

    public record NfcTagRequest(
        @NotBlank(message = "NFC tag UID cannot be empty.")
        @Length(min = 11, max = 20, message = "NFC tag UID must be between 11 and 20 characters long.")
        @Pattern(regexp = Util.NFC_UID_REGEXP, message = "NFC tag UID must be a valid hexadecimal string (with optional colons or hyphens).")
        String uid
    ) {}

    @Autowired
    public NfcController(NfcService nfcService) {
        this._nfcService = nfcService;
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerNfcTag(
        @Validated @RequestBody RegisterNfcTagRequest body
    ) {
        try {
            this._nfcService.registerDispenserNfcTag(body);

            return ResponseEntity.ok(new MessageResponse("NFC tag registered successfully."));
        } catch(InvalidAuthenticationCredentialsException e) {
            throw new HttpException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<MessageResponse> verifyNfcTag(@RequestBody @Validated NfcTagRequest body) {
        if(this._nfcService.verifyDispenserNfcTag(body.uid)) return ResponseEntity.ok(new MessageResponse("NFC tag verified successfully."));
        else return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageResponse("NFC tag verification failed."));
    }
}
