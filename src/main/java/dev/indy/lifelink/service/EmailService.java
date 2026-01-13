package dev.indy.lifelink.service;

import dev.indy.lifelink.model.Medicine;
import dev.indy.lifelink.model.MedicineSchedule;
import dev.indy.lifelink.model.Person;
import dev.indy.lifelink.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender _mailSender;
    private final String _fromAddress;

    @Autowired
    public EmailService(JavaMailSender mailSender, @Value("${spring.mail.username}") String from) {
        this._mailSender = mailSender;
        this._fromAddress = from;
    }

    public boolean isUp() {
        try {
            if(this._mailSender instanceof JavaMailSenderImpl impl) {
                impl.testConnection();
                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        msg.setFrom(this._fromAddress);

        this._mailSender.send(msg);
    }

    public void sendTestEmail(String target) {
        this.sendSimpleMessage(target, "Test email", "This is a test email.");
    }

    public void sendMedicineReminder(String target, MedicineSchedule schedule) {
        Medicine medicine = schedule.getMedicine();

        String msg = "It's time (%s) to take medicine: \n".formatted(schedule.getExecutionTime())
            + " - Medicine name: %s \n".formatted(medicine.getMedicineName())
            + " - Dosage: %s \n".formatted(schedule.getDosage())
            + " - Notes: \n"
            + "%s\n\n%s".formatted(medicine.getNotes(), schedule.getNotes());

        this.sendSimpleMessage(target, "Medicine reminder", msg);
    }

    public void notifyAboutMedicineNotTaken(String target, MedicineSchedule schedule) {
        Person person = schedule.getPatient().getPerson();
        Medicine medicine = schedule.getMedicine();

        String msg = "%s did not take their today's %s medicine yet: \n".formatted(Util.getFullName(person), schedule.getExecutionTime())
            + " - Medicine name: %s \n".formatted(medicine.getMedicineName())
            + " - Dosage: %s \n".formatted(schedule.getDosage())
            + " - Notes: \n"
            + "%s\n\n%s".formatted(medicine.getNotes(), schedule.getNotes());

        this.sendSimpleMessage(target, "Medicine not taken", msg);
    }

    public void notifyAboutMedicineTaken(MedicineSchedule schedule) {
        Person person = schedule.getPatient().getPerson();
        Person contactPerson = schedule.getPatient().getContactPerson();
        Medicine medicine = schedule.getMedicine();

        String msg = "%s just took their today's %s medicine: \n".formatted(Util.getFullName(person), schedule.getExecutionTime())
            + " - Medicine name: %s \n".formatted(medicine.getMedicineName())
            + " - Dosage: %s \n".formatted(schedule.getDosage())
            + " - Notes: \n"
            + "%s\n\n%s".formatted(medicine.getNotes(), schedule.getNotes());

        this.sendSimpleMessage(contactPerson.getEmail(), "Medicine taken", msg);
    }
}
