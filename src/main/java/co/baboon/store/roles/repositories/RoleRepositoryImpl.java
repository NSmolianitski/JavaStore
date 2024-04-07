package co.baboon.store.roles.repositories;

import co.baboon.store.jooq.tables.records.RolesRecord;
import co.baboon.store.roles.Role;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.TableField;

import java.util.List;
import java.util.Optional;

import static co.baboon.store.jooq.Tables.ROLES;

public class RoleRepositoryImpl implements RoleRepository {
    private final DSLContext context;
    
    private final List<TableField<RolesRecord, ?>> ROLE_FIELDS = List.of(
            ROLES.ID,
            ROLES.NAME
    );

    public RoleRepositoryImpl(DSLContext context) {
        this.context = context;
    }

    @Override
    public Optional<Role> getRoleById(Long id) {
        return context.select(ROLE_FIELDS)
                .from(ROLES)
                .where(ROLES.ID.eq(id))
                .fetchOptional(RoleRepositoryImpl::buildRole);
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return context.select(ROLE_FIELDS)
                .from(ROLES)
                .where(ROLES.NAME.eq(name))
                .fetchOptional(RoleRepositoryImpl::buildRole);
    }
    
    private static Role buildRole(Record record) {
        return Role.builder()
                .withId(record.get(ROLES.ID))
                .withName(record.get(ROLES.NAME))
                .build();
    }
}
