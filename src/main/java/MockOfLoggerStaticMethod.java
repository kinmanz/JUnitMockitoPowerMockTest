import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Statement;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * Created by Sergey.Luchko on 30.07.2016.
 */
public class MockOfLoggerStaticMethod {
}

package com.peterservice.openapi.cms.core.support.liquibase;

        import com.google.common.collect.Sets;
        import liquibase.database.Database;
        import liquibase.database.jvm.JdbcConnection;
        import liquibase.resource.ResourceAccessor;
        import org.junit.Before;
        import org.junit.BeforeClass;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.mockito.InOrder;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.Spy;
        import org.powermock.api.mockito.PowerMockito;
        import org.powermock.core.classloader.annotations.PrepareForTest;
        import org.powermock.modules.junit4.PowerMockRunner;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

        import java.io.ByteArrayInputStream;
        import java.io.InputStream;
        import java.nio.charset.StandardCharsets;
        import java.sql.Statement;

        import static org.mockito.Matchers.anyString;
        import static org.mockito.Matchers.isA;
        import static org.mockito.Mockito.inOrder;
        import static org.mockito.Mockito.verify;
        import static org.mockito.internal.verification.VerificationModeFactory.times;
        import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LoggerFactory.class})
public class TestApplySqlFileIfExistsChange {

    @InjectMocks
    ApplySqlFileIfExistsChange applySqlFileIfExistsChange;

    @Mock
    private ResourceAccessor resourceAccessor;

    @Mock
    private JdbcConnection jdbcConnection;

    @Mock
    private Database database;

    @Mock
    Statement statement;

    @BeforeClass
    public static void setUpClass() {
        mockStatic(LoggerFactory.class);
        Logger logger = mock(Logger.class);
        when(LoggerFactory.getLogger(ApplySqlFileIfExistsChange.class)).thenReturn(logger);
    }

    @Before
    public void setUp() throws Exception{
        when(database.getConnection()).thenReturn(jdbcConnection);

        InputStream inp1, inp2;
        inp1 = new ByteArrayInputStream("FirstTestQuery".getBytes(StandardCharsets.UTF_8));
        inp2 = new ByteArrayInputStream("SecondTestQuery".getBytes(StandardCharsets.UTF_8));

        when(resourceAccessor.getResourcesAsStream(anyString())).thenReturn(Sets.newHashSet(inp1, inp2));
        when(jdbcConnection.createStatement()).thenReturn(statement);
    }

    @Test
    public void execute() throws Exception {
        applySqlFileIfExistsChange.execute(database);
        verify(statement).execute("FirstTestQuery");
        verify(statement).execute("SecondTestQuery");
    }

}