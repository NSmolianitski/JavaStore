package co.baboon.store.users.repositories;

import co.baboon.store.jooq.tables.records.UsersRecord;
import co.baboon.store.roles.Role;
import co.baboon.store.users.User;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.TableField;

import java.util.List;
import java.util.Optional;

import static co.baboon.store.jooq.Tables.*;
import org.jooq.*;
import static org.jooq.impl.DSL.*;

public class UserRepositoryImpl implements UserRepository {
    private final DSLContext context;
    
    private final List<TableField<UsersRecord, ?>> USER_FIELDS = List.of(
            USERS.ID,
            USERS.USERNAME,
            USERS.PASSWORD
    );

    public UserRepositoryImpl(DSLContext context) {
        this.context = context;
    }

    @Override
    public User addUser(User user) {
        var returnedUser = context.insertInto(USERS)
                .set(USERS.USERNAME, user.getUsername())
                .set(USERS.PASSWORD, user.getPassword())
                .returning()
                .fetchSingle(UserRepositoryImpl::buildUser);
        
        for (var role : user.roles()) {
            var usersRolesRecord = context.newRecord(USERS_ROLES);
            usersRolesRecord.setUserId(returnedUser.id());
            usersRolesRecord.setRoleId(role.id());
            usersRolesRecord.store();
        }
                
        returnedUser.roles()
                .addAll(user.roles());
        
        return returnedUser;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return context.select(
                        USERS.ID,
                        USERS.USERNAME,
                        USERS.PASSWORD,
                        multisetAgg(ROLES.ID, ROLES.NAME).as("roles")
                                .convertFrom(record -> record.intoSet(Records.mapping(Role::new)))
                )
                .from(USERS)
                    .join(USERS_ROLES).on(USERS.ID.eq(USERS_ROLES.USER_ID))
                    .join(ROLES).on(USERS_ROLES.ROLE_ID.eq(ROLES.ID))
                .where(USERS.ID.eq(id))
                .groupBy(USER_FIELDS)
                .fetchOptional(record ->
                    User.builder()
                            .withId(record.get(USERS.ID))
                            .withUsername(record.get(USERS.USERNAME))
                            .withPassword(record.get(USERS.PASSWORD))
                            .withRoles(record.component4()) // :(
                            .build()
                );
    }

//    @Override
    public Optional<User> getUserById2(Long id) {
        var userRoles = context.select(ROLES.ID, ROLES.NAME)
                .from(ROLES)
                .join(USERS_ROLES).on(ROLES.ID.eq(USERS_ROLES.ROLE_ID))
                .where(USERS_ROLES.USER_ID.eq(id))
                .fetch(UserRepositoryImpl::buildRole);

        var returnedUser = context.select()
                .from(USERS)
                .join(USERS_ROLES).on(USERS.ID.eq(USERS_ROLES.USER_ID))
                .join(ROLES).on(ROLES.ID.eq(USERS_ROLES.ROLE_ID))
                .where(USERS.ID.eq(id))
                .fetchOptional(UserRepositoryImpl::buildUser);

        returnedUser.ifPresent(user -> user.roles()
                .addAll(userRoles));
        return returnedUser;
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return context.select(USER_FIELDS)
                .from(USERS)
                .where(USERS.USERNAME.eq(login))
                .fetchOptional(UserRepositoryImpl::buildUser);
    }

    @Override
    public List<User> getAllUsers() {
        return context.select(USER_FIELDS)
                .from(USERS)
                .fetch(UserRepositoryImpl::buildUser);
    }

    @Override
    public void updateUser(User user) {
        context.update(USERS)
                .set(USERS.USERNAME, user.getUsername())
                .set(USERS.PASSWORD, user.getPassword())
                .where(USERS.ID.eq(user.id()))
                .execute();
    }

    @Override
    public void deleteUserById(Long id) {
        context.delete(USERS)
                .where(USERS.ID.eq(id))
                .execute();
    }

    private static User buildUser(Record record) {
        return User.builder()
                .withId(record.get(USERS.ID))
                .withUsername(record.get(USERS.USERNAME))
                .withPassword(record.get(USERS.PASSWORD))
                .build();
    }
    
    private static Role buildRole(Record record) {
        return Role.builder()
                .withId(record.get(ROLES.ID))
                .withName(record.get(ROLES.NAME))
                .build();
    }
}
