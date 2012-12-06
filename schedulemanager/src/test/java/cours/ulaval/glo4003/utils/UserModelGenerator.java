package cours.ulaval.glo4003.utils;

import cours.ulaval.glo4003.controller.model.UserModel;

public final class UserModelGenerator {

	public static UserModel createUserModel() {
		UserModel model = new UserModel();
		model.setIdul("HABBA");
		model.setName("Derp");
		model.setPassword("HelloILoveKitties");
		model.setEmailAddress("derp@herp.com");

		return model;
	}
}
