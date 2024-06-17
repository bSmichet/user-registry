package fr.airfrance.userregistry.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.airfrance.userregistry.IntegrationTest;
import fr.airfrance.userregistry.domain.User;
import fr.airfrance.userregistry.domain.enumeration.Country;
import fr.airfrance.userregistry.repository.UserRepository;
import fr.airfrance.userregistry.service.dto.UserDTO;
import fr.airfrance.userregistry.service.mapper.UserMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static fr.airfrance.userregistry.domain.UserAsserts.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
class UserResourceIT {

    private static final String VALID_FIRST_NAME = "TOTO";

    private static final String VALID_LAST_NAME = "XOXO";

    private static final Instant VALID_BIRTHDAY = Instant.ofEpochMilli(0L);
    private static final Instant UNDERAGE_BIRTHDAY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String VALID_ADDRESS = "1 avenue De Gaulle";

    private static final String VALID_POSTAL_CODE = "88011";
    private static final String BAD_POSTAL_CODE = "AB32R";
    private static final String DEFAULT_POSTAL_CODE = "75000";

    private static final Country VALID_COUNTRY = Country.FRANCE;
    private static final Country BAD_COUNTRY = Country.TUNISIA;

    private static final String VALID_EMAIL = "default@email.com";
    private static final String BAD_EMAIL = "bademail.com";

    private static final String VALID_PHONE_NUMBER = "0659808596";
    private static final String BAD_PHONE_NUMBER = "085462357486";

    private static final String ENTITY_API_URL = "/api/users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MockMvc restUserMockMvc;

    private User user;

    private User insertedUser;

    /**
     * Create an entity for this test.
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static User createEntity() {
        User user = new User()
                .firstName(VALID_FIRST_NAME)
                .lastName(VALID_LAST_NAME)
                .birthday(VALID_BIRTHDAY)
                .address(VALID_ADDRESS)
                .postalCode(VALID_POSTAL_CODE)
                .country(VALID_COUNTRY)
                .email(VALID_EMAIL)
                .phoneNumber(VALID_PHONE_NUMBER);
        return user;
    }

    @BeforeEach
    public void initTest() {
        user = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedUser != null) {
            userRepository.delete(insertedUser);
            insertedUser = null;
        }
    }

    @Test
    void registerUser() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the User
        UserDTO userDTO = userMapper.toDto(user);
        var returnedUserDTO = om.readValue(
                restUserMockMvc
                        .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userDTO)))
                        .andExpect(status().isCreated())
                        .andReturn()
                        .getResponse()
                        .getContentAsString(),
                UserDTO.class
        );

        // Validate the User in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedUser = userMapper.toEntity(returnedUserDTO);
        assertUserUpdatableFieldsEquals(returnedUser, getPersistedUser(returnedUser));

        insertedUser = returnedUser;
    }

    @Test
    void checkDefaultPostalCode() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();

        // Create the User without postal code
        user.setPostalCode(null);
        UserDTO userDTO = userMapper.toDto(user);

        var returnedUserDTO = om.readValue(
                restUserMockMvc
                        .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userDTO)))
                        .andExpect(status().isCreated())
                        .andReturn()
                        .getResponse()
                        .getContentAsString(),
                UserDTO.class
        );

        // Validate the User in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedUser = userMapper.toEntity(returnedUserDTO);
        assertUserUpdatableFieldsEquals(returnedUser, getPersistedUser(returnedUser));

        assertThat(returnedUser.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);

        insertedUser = returnedUser;
    }

    @Test
    void registerUserWithExistingId() throws Exception {
        // Create the User with an existing ID
        user.setId("existing_id");
        UserDTO userDTO = userMapper.toDto(user);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userDTO)))
                .andExpect(status().isBadRequest());

        // Validate the User in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void checkFirstNameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        user.setFirstName(null);

        // Create the User, which fails.
        UserDTO userDTO = userMapper.toDto(user);

        restUserMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userDTO)))
                .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkLastNameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        user.setLastName(null);

        // Create the User, which fails.
        UserDTO userDTO = userMapper.toDto(user);

        restUserMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userDTO)))
                .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkBirthdayIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        user.setBirthday(null);

        // Create the User, which fails.
        UserDTO userDTO = userMapper.toDto(user);

        restUserMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userDTO)))
                .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkAddressIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        user.setAddress(null);

        // Create the User, which fails.
        UserDTO userDTO = userMapper.toDto(user);

        restUserMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userDTO)))
                .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkCountryIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        user.setCountry(null);

        // Create the User, which fails.
        UserDTO userDTO = userMapper.toDto(user);

        restUserMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userDTO)))
                .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkBadPostalCode() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field with bad value
        user.setPostalCode(BAD_POSTAL_CODE);

        // Create the User, which fails.
        UserDTO userDTO = userMapper.toDto(user);

        restUserMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userDTO)))
                .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkBadEmail() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field with bad value
        user.setPostalCode(BAD_EMAIL);

        // Create the User, which fails.
        UserDTO userDTO = userMapper.toDto(user);

        restUserMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userDTO)))
                .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkBadPhoneNumber() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field with bad value
        user.setPhoneNumber(BAD_PHONE_NUMBER);

        // Create the User, which fails.
        UserDTO userDTO = userMapper.toDto(user);

        restUserMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userDTO)))
                .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkBadCountry() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field with bad value
        user.setCountry(BAD_COUNTRY);

        // Create the User, which fails.
        UserDTO userDTO = userMapper.toDto(user);

        restUserMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userDTO)))
                .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkUnderAge() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field with bad value
        user.setBirthday(UNDERAGE_BIRTHDAY);

        // Create the User, which fails.
        UserDTO userDTO = userMapper.toDto(user);

        restUserMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userDTO)))
                .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void getRegisteredUser() throws Exception {
        // Initialize the database
        insertedUser = userRepository.save(user);

        // Get the user
        restUserMockMvc
                .perform(get(ENTITY_API_URL_ID, user.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.firstName").value(VALID_FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(VALID_LAST_NAME))
                .andExpect(jsonPath("$.birthday").value(VALID_BIRTHDAY.toString()))
                .andExpect(jsonPath("$.address").value(VALID_ADDRESS))
                .andExpect(jsonPath("$.postalCode").value(VALID_POSTAL_CODE))
                .andExpect(jsonPath("$.country").value(VALID_COUNTRY.toString()))
                .andExpect(jsonPath("$.email").value(VALID_EMAIL))
                .andExpect(jsonPath("$.phoneNumber").value(VALID_PHONE_NUMBER));
    }

    @Test
    void getNonExistingUser() throws Exception {
        // Get the user
        restUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    protected long getRepositoryCount() {
        return userRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected User getPersistedUser(User user) {
        return userRepository.findById(user.getId()).orElseThrow();
    }

}
