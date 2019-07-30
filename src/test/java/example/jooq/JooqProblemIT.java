package example.jooq;

import java.io.IOException;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JooqProblemIT {

    private static final Logger LOG = LoggerFactory.getLogger(JooqProblemIT.class);

    private static DSLContext ctx;

    @BeforeClass
    public static void init() throws IOException {
        LOG.info("Initializing connection");

        final String jdbcUrl = buildDatabaseJDBCUrl();
        ctx = DSL.using(jdbcUrl);
    }

    @AfterClass
    public static void destroy() throws IOException {
        ctx.close();
    }

    private static String buildDatabaseJDBCUrl() throws IOException {
        String username = "postgres";
        String postgresIp = System.getProperty("docker.container.database-postgresql.ip");
        int postgresPort = 5432;
        String jdbcUrl = "jdbc:postgresql://" + postgresIp + ":" + postgresPort + "/postgres?user=" + username;

        LOG.info("Postgres JDBC url: {}", jdbcUrl);

        return jdbcUrl;
    }

    @Test
    public void test1() {
        LOG.info("Running test 1");
        final JooqProblem p = new JooqProblem();

        p.doTestOne(ctx);
    }
    
    @Test
    public void test2() {
        LOG.info("Running test 2");
        final JooqProblem p = new JooqProblem();

        p.doTestTwo(ctx);
    }
}
