package dev.indy.lifelink.controller;

import dev.indy.lifelink.model.request.RegisterNfcTagRequest;
import dev.indy.lifelink.model.response.MessageResponse;
import dev.indy.lifelink.service.NfcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nfc")
public class NfcController {
    private final NfcService _nfcService;

    @Autowired
    public NfcController(NfcService nfcService) {
        this._nfcService = nfcService;
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerNfcTag(
        @Validated @RequestBody RegisterNfcTagRequest body
    ) {
        return ResponseEntity.ok(new MessageResponse("NFC tag registered successfully."));
    }

    @PostMapping("/verify")
    public ResponseEntity<MessageResponse> verifyNfcTag() {
        return ResponseEntity.ok(new MessageResponse("NFC tag verified successfully."));
    }
}
