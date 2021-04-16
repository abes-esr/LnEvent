package fr.abes.lnevent.controllers;


import fr.abes.lnevent.entities.ContactEntity;
import fr.abes.lnevent.entities.EtablissementEntity;
import fr.abes.lnevent.entities.EventEntity;
import fr.abes.lnevent.event.password.UpdatePasswordEvent;
import fr.abes.lnevent.recaptcha.ReCaptchaResponse;
import fr.abes.lnevent.repository.ContactRepository;
import fr.abes.lnevent.repository.EtablissementRepository;
import fr.abes.lnevent.repository.EventRepository;
import fr.abes.lnevent.security.jwt.JwtTokenProvider;
import fr.abes.lnevent.security.services.impl.UserDetailsImpl;
import fr.abes.lnevent.security.services.impl.UserDetailsServiceImpl;
import fr.abes.lnevent.services.EmailService;
import fr.abes.lnevent.services.ReCaptchaCreationCompteService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ln/reinitialisationMotDePasse")
public class PasswordController {


    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    public JavaMailSender mailSender;

    @Autowired
    EmailService emailService;

    @Autowired
    EtablissementRepository etablissementRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    private ReCaptchaCreationCompteService reCaptchaCreationCompteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserDetails userDetails;

    private String emailUser;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private EventRepository eventRepository;


    @ApiOperation(value = "permet de ",
            notes = "le ")
    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(HttpServletRequest request, @Valid @RequestBody String userEmailOrSiren) throws JSONException {

        log.info("userEmailOrSiren = " + userEmailOrSiren);
        JSONObject emailOrSiren = new JSONObject(userEmailOrSiren);
        String param = emailOrSiren.keys().next().toString();
        log.info("param = " + param);
        String mail = null;
        String siren = null;
        EtablissementEntity user = null;
        if (param.equals("email")) {
            mail = emailOrSiren.getString("email");
            log.info("mail = " + mail);
            user = etablissementRepository.getUserByMail(mail);
        } else {
            siren = emailOrSiren.getString("siren");
            user = etablissementRepository.getFirstBySiren(siren);
        }
        if (param.equals("email") && user == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Le mail renseigné n'a pas été trouvé.");
        } else if (param.equals("siren") && user == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Le siren renseigné n'a pas été trouvé.");
        }
        userDetails = new UserDetailsServiceImpl().loadUser(user);

        String jwt = tokenProvider.generateToken((UserDetailsImpl) userDetails);
        String url = emailService.getAppUrl(request);
        emailUser = ((UserDetailsImpl) userDetails).getEmail();
        mailSender.send(emailService.constructResetTokenEmail(url,
                request.getLocale(), jwt, emailUser));

        return ResponseEntity.ok("Nous venons de vous envoyer un mail de réinitialisation de mot de passe.");
    }


    @ApiOperation(value = "permet de ",
            notes = "le ")
    @PostMapping("/enregistrerPassword")
    public ResponseEntity<?> enregistrerPassword(HttpServletRequest request, @Valid @RequestBody String requestData) throws JSONException {
        log.info("requestData = " + requestData);
        JSONObject data = new JSONObject(requestData);
        String recaptcha = data.getString("recaptcha");
        log.info("recaptcha = " + recaptcha);
        String mdp = data.getString("motDePasse");
        log.info("mdp = " + mdp);
        String token = data.getString("token");
        log.info("token = " + token);
        String action = "reinitialisationPass";

        //verifier la réponse recaptcha
        ReCaptchaResponse reCaptchaResponse = reCaptchaCreationCompteService.verify(recaptcha, action);
        if (!reCaptchaResponse.isSuccess()) {
            return ResponseEntity
                    .badRequest()
                    .body("Erreur ReCaptcha : " + reCaptchaResponse.getErrors());
        }
        if (!tokenProvider.validateToken(token)) {
            return ResponseEntity
                    .badRequest()
                    .body("Token invalide");
        }
        String siren = tokenProvider.getSirenFromJwtToken(token);
        log.info("siren = " + siren);
        String mdphash = passwordEncoder.encode(mdp);
        log.info("mdphash = " + mdp);
        EtablissementEntity e = etablissementRepository.getFirstBySiren(siren);
        ContactEntity c = e.getContact();
        c.setMotDePasse(mdphash);
        contactRepository.save(c);
        emailUser = c.getMail();
        mailSender.send(emailService.constructValidationNewPassEmail(request.getLocale(), emailUser));
        return ResponseEntity.ok("Votre mot de passe a bien été réinitialisé. Nous venons de vous envoyer un mail de confirmation de réinitialisation de mot de passe.");
    }

    @ApiOperation(value = "permet de mettre à jour le mot de passe une fois connecté")
    @PostMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(HttpServletRequest request, @Valid @RequestBody String requestData) throws JSONException {
        JSONObject data = new JSONObject(requestData);
        String siren = tokenProvider.getSirenFromJwtToken(tokenProvider.getJwtFromRequest(request));
        String oldPassword = data.getString("oldPassword");
        String newPasswordHash = passwordEncoder.encode(data.getString("newPassword"));

        EtablissementEntity e = etablissementRepository.getFirstBySiren(siren);
        ContactEntity c = e.getContact();

        if (passwordEncoder.matches(oldPassword, c.getMotDePasse())) {
            UpdatePasswordEvent updatePasswordEvent = new UpdatePasswordEvent(this, siren, newPasswordHash);
            applicationEventPublisher.publishEvent(updatePasswordEvent);
            eventRepository.save(new EventEntity(updatePasswordEvent));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le mot de passe renseigné ne correspond pas à votre ancien mot de passe.");
        }

        return ResponseEntity.ok("Mot de passe mis à jour.");
    }
}






