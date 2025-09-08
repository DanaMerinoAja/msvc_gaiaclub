package org.soygaia.msvc.gaiaclub.services;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;

@ApplicationScoped
public class MailService {

    @Inject
    Mailer mailer;

    @Location("mail.html")
    Template expiryTpl;

    public void sendExpiryAlert(String to,
                                long pointsToExpire,
                                LocalDate expirationDate) {
        TemplateInstance ti = expiryTpl
                .data("points", pointsToExpire)
                .data("expirationDate", expirationDate);

        mailer.send(Mail.withHtml(to,
                "Tus puntos vencen el " + expirationDate,
                ti.render()));
    }

}
