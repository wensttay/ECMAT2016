package io.github.herocode.ecmat.interfaces;

import io.github.herocode.ecmat.entity.Participant;
import io.github.herocode.ecmat.entity.Payment;
import io.github.herocode.ecmat.entity.ShortCourse;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public interface ParticipantBusiness {

    Participant saveParticipant(Participant participant, String paymentId);

    Participant saveParticipant(Participant participant, Payment payment);

    boolean updateParticipant(Participant participant);

    boolean deleteParticipant(Participant participant);

    boolean existsEmail(String email);

    boolean needRecover(String email);

    List<Participant> listAllParticipants();

    Participant searchParticipantById(int id) throws IllegalArgumentException;

    Participant login(String email, String password) throws IllegalArgumentException;

    Participant searchParticipantByEmail(String email) throws IllegalArgumentException;

    List<ShortCourse> getRegisteredShortCourse(Participant participant);

    List<Participant> searchParticipantByAttribute(String key, String value);

    List<Participant> searchParticipantByAttributes(Map<String, String> map);

    String getEmailFromPaymentReference(String paymentReference);

}
