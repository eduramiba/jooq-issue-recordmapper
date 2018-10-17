package example.jooq;

import example.test.jooq.database.tables.TestTable;
import static example.test.jooq.database.tables.TestTable.TEST_TABLE;
import example.test.jooq.database.tables.TestTableOther;
import static example.test.jooq.database.tables.TestTableOther.TEST_TABLE_OTHER;
import example.test.jooq.database.udt.records.MeasurementReferenceRecord;
import example.test.jooq.database.udt.records.PredictionRecord;
import java.math.BigDecimal;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;

public class JooqProblem {

    private static final TestTable T1 = TEST_TABLE.as("t1");
    private static final TestTableOther T2 = TEST_TABLE_OTHER.as("t2");

    public void doTestOne(DSLContext ctx) {

        final PredictionRecord r1 = new PredictionRecord();
        r1.setLabel("test1");
        r1.setX1(BigDecimal.valueOf(0.1));
        r1.setY1(BigDecimal.valueOf(0.1));
        r1.setX2(BigDecimal.valueOf(0.2));
        r1.setY2(BigDecimal.valueOf(0.2));
        r1.setConfidence(BigDecimal.ONE);

        final PredictionRecord r2 = new PredictionRecord();
        r2.setLabel("test2");
        r2.setX1(BigDecimal.valueOf(0.3));
        r2.setY1(BigDecimal.valueOf(0.3));
        r2.setX2(BigDecimal.valueOf(0.4));
        r2.setY2(BigDecimal.valueOf(0.5));
        r2.setConfidence(BigDecimal.ZERO);

        ctx.insertInto(T1, T1.ID, T1.DATA, T1.DATA_TWO)
                .values(
                        1,
                        new PredictionRecord[]{
                            r1
                        },
                        new PredictionRecord[]{
                            r2
                        }
                )
                .execute();

        Field<PredictionRecord[]> select = ctx.select(T1.DATA)
                .from(T1)
                .where(T1.ID.eq(1))
                .asField();

        ctx.insertInto(T1, T1.ID, T1.DATA, T1.DATA_TWO)
                .values(
                        DSL.val(2),
                        DSL.val(
                                new PredictionRecord[]{
                                    r1,
                                    r2
                                }
                        ),
                        select
                )
                .execute();
    }

    public void doTestTwo(DSLContext ctx) {

        final MeasurementReferenceRecord r1 = new MeasurementReferenceRecord();
        r1.setSourceName("test1");
        r1.setX1(BigDecimal.valueOf(0.1));
        r1.setY1(BigDecimal.valueOf(0.1));
        r1.setX2(BigDecimal.valueOf(0.2));
        r1.setY2(BigDecimal.valueOf(0.2));
        r1.setDistanceMilimeters(BigDecimal.ONE);

        final MeasurementReferenceRecord r2 = new MeasurementReferenceRecord();
        r2.setSourceName("test2");
        r2.setX1(BigDecimal.valueOf(0.3));
        r2.setY1(BigDecimal.valueOf(0.3));
        r2.setX2(BigDecimal.valueOf(0.4));
        r2.setY2(BigDecimal.valueOf(0.5));
        r2.setDistanceMilimeters(BigDecimal.ZERO);

        ctx.insertInto(T2, T2.ID, T2.DATA, T2.DATA_TWO)
                .values(
                        1,
                        new MeasurementReferenceRecord[]{
                            r1
                        },
                        new MeasurementReferenceRecord[]{
                            r2
                        }
                )
                .execute();

        Field<MeasurementReferenceRecord[]> select = ctx.select(T2.DATA)
                .from(T2)
                .where(T2.ID.eq(1))
                .asField();

        ctx.insertInto(T2, T2.ID, T2.DATA, T2.DATA_TWO)
                .values(
                        DSL.val(2),
                        DSL.val(
                                new MeasurementReferenceRecord[]{
                                    r1,
                                    r2
                                }
                        ),
                        select
                )
                .execute();
    }

}
