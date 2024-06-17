package fr.airfrance.userregistry.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static fr.airfrance.userregistry.domain.UserAsserts.*;
import static fr.airfrance.userregistry.domain.UserTestSamples.*;

class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getUserSample1();
        var actual = userMapper.toEntity(userMapper.toDto(expected));
        assertUserAllPropertiesEquals(expected, actual);
    }
}
