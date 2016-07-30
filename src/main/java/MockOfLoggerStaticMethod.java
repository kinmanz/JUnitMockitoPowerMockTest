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