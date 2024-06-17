package fr.airfrance.userregistry.domain;

import fr.airfrance.userregistry.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static fr.airfrance.userregistry.domain.UserTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(User.class);
        User user1 = getUserSample1();
        User user2 = new User();
        assertThat(user1).isNotEqualTo(user2);

        user2.setId(user1.getId());
        assertThat(user1).isEqualTo(user2);

        user2 = getUserSample2();
        assertThat(user1).isNotEqualTo(user2);
    }
}
