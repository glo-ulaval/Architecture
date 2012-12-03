package cours.ulaval.glo4003.controller.model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import cours.ulaval.glo4003.domain.Role;
import cours.ulaval.glo4003.domain.User;
import cours.ulaval.glo4003.utils.UserModelGenerator;

public class UserModelTest {

	@Test
	public void canConvertBasicUserModelToUser() {
		UserModel model = UserModelGenerator.createUserModel();

		User user = model.convertToUser();

		assertEquals(user.getIdul(), model.getIdul());
		assertEquals(user.getName(), model.getName());
		assertEquals(user.getPassword(), model.getPassword());
		assertEquals(user.getEmailAddress(), model.getEmailAddress());
	}

	@Test
	public void canConvertUserModelWithRolesToUser() {
		UserModel model = UserModelGenerator.createUserModel();
		model.setRoles(Arrays.asList("Administrateur", "Enseignant"));

		User user = model.convertToUser();

		List<Role> roles = user.getRoles();
		assertEquals(2, roles.size());
		assertTrue(roles.contains(Role.ROLE_Administrateur));
		assertTrue(roles.contains(Role.ROLE_Enseignant));
	}
}
