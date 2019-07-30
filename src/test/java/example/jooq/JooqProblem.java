package example.jooq;

import example.test.jooq.database.tables.Thing;
import example.test.jooq.database.tables.User;
import java.time.OffsetDateTime;
import org.jooq.DSLContext;
import org.junit.Assert;

public class JooqProblem {

    private static final User USER = User.USER.as("u");
    private static final Thing THING = Thing.THING.as("t");

    public void doTestOne(final DSLContext ctx) {
        final int thingId = 10_000;
        final int userId = 1;
        final String userName = "Test";
        final String desc = "Some text";

        ctx.insertInto(USER)
                .columns(USER.ID, USER.NAME)
                .values(userId, userName)
                .execute();

        final OffsetDateTime now = OffsetDateTime.now();
        ctx.insertInto(THING)
                .columns(THING.ID, THING.DESCRIPTION, THING.TIMESTAMP, THING.USER_ID)
                .values(thingId, desc, now, userId)
                .execute();

        final ThingModel thing = ctx.select(
                THING.ID,
                THING.DESCRIPTION,
                USER.ID.as("auditInfo.user.id"),
                USER.NAME.as("auditInfo.user.name"),
                THING.TIMESTAMP.as("auditInfo.timestamp")
        ).from(THING)
                .join(USER).on(USER.ID.eq(THING.USER_ID))
                .where(THING.ID.eq(thingId))
                .fetchOne(r -> r.into(ThingModel.Builder.class).build());

        Assert.assertNotNull(thing);
        Assert.assertEquals(thingId, (int) thing.getId());
        Assert.assertEquals(desc, thing.getDescription());

        Assert.assertNotNull(thing.getAuditInfo());//Fails here, cannot pick complex type in constructor (all constructors have @ConstructorProperties annotation)
        Assert.assertEquals(now, thing.getAuditInfo().getTimestamp());

        Assert.assertNotNull(thing.getAuditInfo().getUser());
        Assert.assertEquals(userId, (int) thing.getAuditInfo().getUser().getId());
        Assert.assertEquals(userName, thing.getAuditInfo().getUser().getName());
    }

    public void doTestTwo(final DSLContext ctx) {
        final int thingId = 20_000;
        final int userId = 2;
        final String userName = "Test 2";
        final String desc = "Some text 2";

        ctx.insertInto(USER)
                .columns(USER.ID, USER.NAME)
                .values(userId, userName)
                .execute();

        final OffsetDateTime now = OffsetDateTime.now();
        ctx.insertInto(THING)
                .columns(THING.ID, THING.DESCRIPTION, THING.TIMESTAMP, THING.USER_ID)
                .values(thingId, desc, now, userId)
                .execute();

        final ThingModel thing = ctx.select(
                THING.ID,
                THING.DESCRIPTION,
                USER.ID.as("auditInfo.user.id"),
                USER.NAME.as("auditInfo.user.name"),
                THING.TIMESTAMP.as("auditInfo.timestamp")
        ).from(THING)
                .join(USER).on(USER.ID.eq(THING.USER_ID))
                .where(THING.ID.eq(thingId))
                .fetchOne(r -> r.into(ThingModel.Builder.class).build());

        Assert.assertNotNull(thing);
        Assert.assertEquals(thingId, (int) thing.getId());
        Assert.assertEquals(desc, thing.getDescription());

        Assert.assertNotNull(thing.getAuditInfo());
        Assert.assertEquals(now, thing.getAuditInfo().getTimestamp());

        Assert.assertNotNull(thing.getAuditInfo().getUser());//Fails here, audit info is fine but not user. It works thanks to using a builder
        Assert.assertEquals(userId, (int) thing.getAuditInfo().getUser().getId());
        Assert.assertEquals(userName, thing.getAuditInfo().getUser().getName());
    }
}
