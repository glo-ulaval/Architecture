package cours.ulaval.glo4003.domain.notification;

public class NewScheduleNotification extends AbstractNotification {

	public NewScheduleNotification(String path) {
		this.message = "Un nouvel horaire est disponible pour approbation.";
		this.referencePath = path;
	}
}
