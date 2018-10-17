Run this with `mvn install`. Needs docker and port 5432 available for PostgreSQL.

Should produce an error:

```java
[ERROR] test(example.jooq.JooqProblemIT)  Time elapsed: 0.611 s  <<< ERROR!
org.jooq.exception.DataAccessException: 
SQL [insert into "jooq_test_catalog"."test_table_other" as "t2" ("id", "data", "data_two") values (?, ?::measurement_reference[], ?::measurement_reference[])]; ERROR: type "measurement_reference[]" does not exist
  Position: 103
	at org.jooq_3.11.5.POSTGRES_10.debug(Unknown Source)
	at org.jooq.impl.Tools.translate(Tools.java:2384)
	at org.jooq.impl.DefaultExecuteContext.sqlException(DefaultExecuteContext.java:811)
	at org.jooq.impl.AbstractQuery.execute(AbstractQuery.java:364)
	at org.jooq.impl.AbstractDelegatingQuery.execute(AbstractDelegatingQuery.java:127)
	at example.jooq.JooqProblem.doTestTwo(JooqProblem.java:96)
	at example.jooq.JooqProblemIT.test(JooqProblemIT.java:48)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:26)
	at org.junit.internal.runners.statements.RunAfters.evaluate(RunAfters.java:27)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.apache.maven.surefire.junit4.JUnit4Provider.execute(JUnit4Provider.java:365)
	at org.apache.maven.surefire.junit4.JUnit4Provider.executeWithRerun(JUnit4Provider.java:273)
	at org.apache.maven.surefire.junit4.JUnit4Provider.executeTestSet(JUnit4Provider.java:238)
	at org.apache.maven.surefire.junit4.JUnit4Provider.invoke(JUnit4Provider.java:159)
	at org.apache.maven.surefire.booter.ForkedBooter.invokeProviderInSameClassLoader(ForkedBooter.java:383)
	at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:344)
	at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:125)
	at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:417)
Caused by: org.postgresql.util.PSQLException: ERROR: type "measurement_reference[]" does not exist
  Position: 103
	at org.postgresql.core.v3.QueryExecutorImpl.receiveErrorResponse(QueryExecutorImpl.java:2440)
	at org.postgresql.core.v3.QueryExecutorImpl.processResults(QueryExecutorImpl.java:2183)
	at org.postgresql.core.v3.QueryExecutorImpl.execute(QueryExecutorImpl.java:308)
	at org.postgresql.jdbc.PgStatement.executeInternal(PgStatement.java:441)
	at org.postgresql.jdbc.PgStatement.execute(PgStatement.java:365)
	at org.postgresql.jdbc.PgPreparedStatement.executeWithFlags(PgPreparedStatement.java:143)
	at org.postgresql.jdbc.PgPreparedStatement.execute(PgPreparedStatement.java:132)
	at org.jooq.tools.jdbc.DefaultPreparedStatement.execute(DefaultPreparedStatement.java:209)
	at org.jooq.impl.AbstractQuery.execute(AbstractQuery.java:432)
	at org.jooq.impl.AbstractDMLQuery.execute(AbstractDMLQuery.java:613)
	at org.jooq.impl.AbstractQuery.execute(AbstractQuery.java:350)
	... 30 more
```
