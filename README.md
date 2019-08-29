# What this tests

This tests that JooQ codegen 3.12.0 cannot read PostgreSQL 11.5 tables correctly.

# How to Run

Run this with `mvn install`. Needs docker and port 5432 available for PostgreSQL.

Should produce an error:

```java
ERROR:  syntax error at or near "select" at character 1122
STATEMENT:  select "tables"."table_schema", "tables"."table_name", "tables"."specific_name", "tables"."table_valued_function", "tables"."materialized_view", "tables"."description" from ((select "information_schema"."tables"."table_schema", "information_schema"."tables"."table_name", "information_schema"."tables"."table_name" as "specific_name", false as "table_valued_function", false as "materialized_view", "pg_catalog"."pg_description"."description" from "information_schema"."tables" join "pg_catalog"."pg_namespace" on "information_schema"."tables"."table_schema" = "pg_catalog"."pg_namespace"."nspname" join "pg_catalog"."pg_class" on ("pg_catalog"."pg_class"."relname" = "information_schema"."tables"."table_name" and "pg_catalog"."pg_class"."relnamespace" = "pg_catalog"."pg_namespace".oid) left outer join "pg_catalog"."pg_description" on ("pg_catalog"."pg_description"."objoid" = "pg_catalog"."pg_class".oid and "pg_catalog"."pg_description"."objsubid" = $1) where ("information_schema"."tables"."table_schema" in ($2) and ("information_schema"."tables"."table_schema", "information_schema"."tables"."table_name") not in select "pg_catalog"."pg_namespace"."nspname", "pg_catalog"."pg_class"."relname" from "pg_catalog"."pg_class" join "pg_catalog"."pg_namespace" on "pg_catalog"."pg_class"."relnamespace" = "pg_catalog"."pg_namespace".oid where "pg_catalog"."pg_class"."relkind" = 'm')) union all (select "pg_catalog"."pg_namespace"."nspname"::varchar, "pg_catalog"."pg_class"."relname"::varchar, "pg_catalog"."pg_class"."relname"::varchar, false as "table_valued_function", true as "materialized_view", "pg_catalog"."pg_description"."description" from "pg_catalog"."pg_class" join "pg_catalog"."pg_namespace" on "pg_catalog"."pg_class"."relnamespace" = "pg_catalog"."pg_namespace".oid left outer join "pg_catalog"."pg_description" on ("pg_catalog"."pg_description"."objoid" = "pg_catalog"."pg_class".oid and "pg_catalog"."pg_description"."objsubid" = $3) where ("pg_catalog"."pg_namespace"."nspname" in ($4) and "pg_catalog"."pg_class"."relkind" = 'm')) union all (select "information_schema"."routines"."routine_schema", "information_schema"."routines"."routine_name", "information_schema"."routines"."specific_name", true as "table_valued_function", false as "materialized_view", '' from "information_schema"."routines" join "pg_catalog"."pg_namespace" on "information_schema"."routines"."specific_schema" = "pg_catalog"."pg_namespace"."nspname" join "pg_catalog"."pg_proc" on ("pg_catalog"."pg_proc"."pronamespace" = "pg_catalog"."pg_namespace".oid and (("pg_catalog"."pg_proc"."proname" || $5) || cast("pg_catalog"."pg_proc".oid as varchar)) = "information_schema"."routines"."specific_name") where ("information_schema"."routines"."routine_schema" in ($6) and "pg_catalog"."pg_proc"."proretset"))) as "tables" order by 1, 2
[WARNING] SQL exception            : Exception while executing meta query: ERROR: syntax error at or near "select"
  Position: 1122

If you think this is a bug in jOOQ, please report it here: https://github.com/jOOQ/jOOQ/issues/new

```sql
select 
  "tables"."table_schema", 
  "tables"."table_name", 
  "tables"."specific_name", 
  "tables"."table_valued_function", 
  "tables"."materialized_view", 
  "tables"."description"
from (
  (
    select 
      "information_schema"."tables"."table_schema", 
      "information_schema"."tables"."table_name", 
      "information_schema"."tables"."table_name" as "specific_name", 
      false as "table_valued_function", 
      false as "materialized_view", 
      "pg_catalog"."pg_description"."description"
    from "information_schema"."tables"
      join "pg_catalog"."pg_namespace"
        on "information_schema"."tables"."table_schema" = "pg_catalog"."pg_namespace"."nspname"
      join "pg_catalog"."pg_class"
        on (
          "pg_catalog"."pg_class"."relname" = "information_schema"."tables"."table_name"
          and "pg_catalog"."pg_class"."relnamespace" = "pg_catalog"."pg_namespace".oid
        )
      left outer join "pg_catalog"."pg_description"
        on (
          "pg_catalog"."pg_description"."objoid" = "pg_catalog"."pg_class".oid
          and "pg_catalog"."pg_description"."objsubid" = 0
        )
    where (
      "information_schema"."tables"."table_schema" in ('jooq_test_catalog')
      and ("information_schema"."tables"."table_schema", "information_schema"."tables"."table_name") not in 
        select 
          "pg_catalog"."pg_namespace"."nspname", 
          "pg_catalog"."pg_class"."relname"
        from "pg_catalog"."pg_class"
          join "pg_catalog"."pg_namespace"
            on "pg_catalog"."pg_class"."relnamespace" = "pg_catalog"."pg_namespace".oid
        where "pg_catalog"."pg_class"."relkind" = 'm'
      
    )
  )
  union all (
    select 
      "pg_catalog"."pg_namespace"."nspname"::varchar, 
      "pg_catalog"."pg_class"."relname"::varchar, 
      "pg_catalog"."pg_class"."relname"::varchar, 
      false as "table_valued_function", 
      true as "materialized_view", 
      "pg_catalog"."pg_description"."description"
    from "pg_catalog"."pg_class"
      join "pg_catalog"."pg_namespace"
        on "pg_catalog"."pg_class"."relnamespace" = "pg_catalog"."pg_namespace".oid
      left outer join "pg_catalog"."pg_description"
        on (
          "pg_catalog"."pg_description"."objoid" = "pg_catalog"."pg_class".oid
          and "pg_catalog"."pg_description"."objsubid" = 0
        )
    where (
      "pg_catalog"."pg_namespace"."nspname" in ('jooq_test_catalog')
      and "pg_catalog"."pg_class"."relkind" = 'm'
    )
  )
  union all (
    select 
      "information_schema"."routines"."routine_schema", 
      "information_schema"."routines"."routine_name", 
      "information_schema"."routines"."specific_name", 
      true as "table_valued_function", 
      false as "materialized_view", 
      ''
    from "information_schema"."routines"
      join "pg_catalog"."pg_namespace"
        on "information_schema"."routines"."specific_schema" = "pg_catalog"."pg_namespace"."nspname"
      join "pg_catalog"."pg_proc"
        on (
          "pg_catalog"."pg_proc"."pronamespace" = "pg_catalog"."pg_namespace".oid
          and (("pg_catalog"."pg_proc"."proname" || '_') || cast("pg_catalog"."pg_proc".oid as varchar)) = "information_schema"."routines"."specific_name"
        )
    where (
      "information_schema"."routines"."routine_schema" in ('jooq_test_catalog')
      and "pg_catalog"."pg_proc"."proretset"
    )
  )
) as "tables"
order by 
  1, 
  2
```

[ERROR] Error while fetching tables
org.jooq.exception.DataAccessException: SQL [select "tables"."table_schema", "tables"."table_name", "tables"."specific_name", "tables"."table_valued_function", "tables"."materialized_view", "tables"."description" from ((select "information_schema"."tables"."table_schema", "information_schema"."tables"."table_name", "information_schema"."tables"."table_name" as "specific_name", false as "table_valued_function", false as "materialized_view", "pg_catalog"."pg_description"."description" from "information_schema"."tables" join "pg_catalog"."pg_namespace" on "information_schema"."tables"."table_schema" = "pg_catalog"."pg_namespace"."nspname" join "pg_catalog"."pg_class" on ("pg_catalog"."pg_class"."relname" = "information_schema"."tables"."table_name" and "pg_catalog"."pg_class"."relnamespace" = "pg_catalog"."pg_namespace".oid) left outer join "pg_catalog"."pg_description" on ("pg_catalog"."pg_description"."objoid" = "pg_catalog"."pg_class".oid and "pg_catalog"."pg_description"."objsubid" = ?) where ("information_schema"."tables"."table_schema" in (?) and ("information_schema"."tables"."table_schema", "information_schema"."tables"."table_name") not in select "pg_catalog"."pg_namespace"."nspname", "pg_catalog"."pg_class"."relname" from "pg_catalog"."pg_class" join "pg_catalog"."pg_namespace" on "pg_catalog"."pg_class"."relnamespace" = "pg_catalog"."pg_namespace".oid where "pg_catalog"."pg_class"."relkind" = 'm')) union all (select "pg_catalog"."pg_namespace"."nspname"::varchar, "pg_catalog"."pg_class"."relname"::varchar, "pg_catalog"."pg_class"."relname"::varchar, false as "table_valued_function", true as "materialized_view", "pg_catalog"."pg_description"."description" from "pg_catalog"."pg_class" join "pg_catalog"."pg_namespace" on "pg_catalog"."pg_class"."relnamespace" = "pg_catalog"."pg_namespace".oid left outer join "pg_catalog"."pg_description" on ("pg_catalog"."pg_description"."objoid" = "pg_catalog"."pg_class".oid and "pg_catalog"."pg_description"."objsubid" = ?) where ("pg_catalog"."pg_namespace"."nspname" in (?) and "pg_catalog"."pg_class"."relkind" = 'm')) union all (select "information_schema"."routines"."routine_schema", "information_schema"."routines"."routine_name", "information_schema"."routines"."specific_name", true as "table_valued_function", false as "materialized_view", '' from "information_schema"."routines" join "pg_catalog"."pg_namespace" on "information_schema"."routines"."specific_schema" = "pg_catalog"."pg_namespace"."nspname" join "pg_catalog"."pg_proc" on ("pg_catalog"."pg_proc"."pronamespace" = "pg_catalog"."pg_namespace".oid and (("pg_catalog"."pg_proc"."proname" || ?) || cast("pg_catalog"."pg_proc".oid as varchar)) = "information_schema"."routines"."specific_name") where ("information_schema"."routines"."routine_schema" in (?) and "pg_catalog"."pg_proc"."proretset"))) as "tables" order by 1, 2]; ERROR: syntax error at or near "select"
  Position: 1122
    at org.jooq_3.12.0.POSTGRES.debug (Unknown Source)
    at org.jooq.impl.Tools.translate (Tools.java:2717)
    at org.jooq.impl.DefaultExecuteContext.sqlException (DefaultExecuteContext.java:755)
    at org.jooq.impl.AbstractQuery.execute (AbstractQuery.java:383)
    at org.jooq.impl.AbstractResultQuery.fetch (AbstractResultQuery.java:353)
    at org.jooq.impl.SelectImpl.fetch (SelectImpl.java:2693)
    at org.jooq.meta.postgres.PostgresDatabase.getTables0 (PostgresDatabase.java:445)
    at org.jooq.meta.AbstractDatabase.getTables (AbstractDatabase.java:1507)
    at org.jooq.codegen.JavaGenerator.generateSchemaIfEmpty (JavaGenerator.java:429)
    at org.jooq.codegen.JavaGenerator.generateCatalogIfEmpty (JavaGenerator.java:414)
    at org.jooq.codegen.JavaGenerator.generate (JavaGenerator.java:388)
    at org.jooq.codegen.GenerationTool.run0 (GenerationTool.java:800)
    at org.jooq.codegen.GenerationTool.run (GenerationTool.java:221)
    at org.jooq.codegen.GenerationTool.generate (GenerationTool.java:216)
    at org.jooq.codegen.maven.Plugin.execute (Plugin.java:198)
    at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo (DefaultBuildPluginManager.java:137)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:210)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:156)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:148)
    at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:117)
    at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:81)
    at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build (SingleThreadedBuilder.java:56)
    at org.apache.maven.lifecycle.internal.LifecycleStarter.execute (LifecycleStarter.java:128)
    at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:305)
    at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:192)
    at org.apache.maven.DefaultMaven.execute (DefaultMaven.java:105)
    at org.apache.maven.cli.MavenCli.execute (MavenCli.java:956)
    at org.apache.maven.cli.MavenCli.doMain (MavenCli.java:288)
    at org.apache.maven.cli.MavenCli.main (MavenCli.java:192)
    at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0 (Native Method)
    at jdk.internal.reflect.NativeMethodAccessorImpl.invoke (NativeMethodAccessorImpl.java:62)
    at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke (DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke (Method.java:567)
    at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced (Launcher.java:282)
    at org.codehaus.plexus.classworlds.launcher.Launcher.launch (Launcher.java:225)
    at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode (Launcher.java:406)
    at org.codehaus.plexus.classworlds.launcher.Launcher.main (Launcher.java:347)
Caused by: org.postgresql.util.PSQLException: ERROR: syntax error at or near "select"
  Position: 1122
    at org.postgresql.core.v3.QueryExecutorImpl.receiveErrorResponse (QueryExecutorImpl.java:2468)
    at org.postgresql.core.v3.QueryExecutorImpl.processResults (QueryExecutorImpl.java:2211)
    at org.postgresql.core.v3.QueryExecutorImpl.execute (QueryExecutorImpl.java:309)
    at org.postgresql.jdbc.PgStatement.executeInternal (PgStatement.java:446)
    at org.postgresql.jdbc.PgStatement.execute (PgStatement.java:370)
    at org.postgresql.jdbc.PgPreparedStatement.executeWithFlags (PgPreparedStatement.java:149)
    at org.postgresql.jdbc.PgPreparedStatement.execute (PgPreparedStatement.java:138)
    at org.jooq.tools.jdbc.DefaultPreparedStatement.execute (DefaultPreparedStatement.java:209)
    at org.jooq.impl.Tools.executeStatementAndGetFirstResultSet (Tools.java:3928)
    at org.jooq.impl.AbstractResultQuery.execute (AbstractResultQuery.java:294)
    at org.jooq.impl.AbstractQuery.execute (AbstractQuery.java:369)
    at org.jooq.impl.AbstractResultQuery.fetch (AbstractResultQuery.java:353)
    at org.jooq.impl.SelectImpl.fetch (SelectImpl.java:2693)
    at org.jooq.meta.postgres.PostgresDatabase.getTables0 (PostgresDatabase.java:445)
    at org.jooq.meta.AbstractDatabase.getTables (AbstractDatabase.java:1507)
    at org.jooq.codegen.JavaGenerator.generateSchemaIfEmpty (JavaGenerator.java:429)
    at org.jooq.codegen.JavaGenerator.generateCatalogIfEmpty (JavaGenerator.java:414)
    at org.jooq.codegen.JavaGenerator.generate (JavaGenerator.java:388)
    at org.jooq.codegen.GenerationTool.run0 (GenerationTool.java:800)
    at org.jooq.codegen.GenerationTool.run (GenerationTool.java:221)
    at org.jooq.codegen.GenerationTool.generate (GenerationTool.java:216)
    at org.jooq.codegen.maven.Plugin.execute (Plugin.java:198)
    at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo (DefaultBuildPluginManager.java:137)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:210)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:156)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:148)
    at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:117)
    at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:81)
    at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build (SingleThreadedBuilder.java:56)
    at org.apache.maven.lifecycle.internal.LifecycleStarter.execute (LifecycleStarter.java:128)
    at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:305)
    at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:192)
    at org.apache.maven.DefaultMaven.execute (DefaultMaven.java:105)
    at org.apache.maven.cli.MavenCli.execute (MavenCli.java:956)
    at org.apache.maven.cli.MavenCli.doMain (MavenCli.java:288)
    at org.apache.maven.cli.MavenCli.main (MavenCli.java:192)
    at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0 (Native Method)
    at jdk.internal.reflect.NativeMethodAccessorImpl.invoke (NativeMethodAccessorImpl.java:62)
    at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke (DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke (Method.java:567)
    at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced (Launcher.java:282)
    at org.codehaus.plexus.classworlds.launcher.Launcher.launch (Launcher.java:225)
    at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode (Launcher.java:406)
    at org.codehaus.plexus.classworlds.launcher.Launcher.main (Launcher.java:347)
```
