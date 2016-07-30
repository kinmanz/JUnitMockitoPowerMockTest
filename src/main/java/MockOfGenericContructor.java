
//This annotation tells PowerMock to prepare certain classes for testing. Classes needed to be defined using
// this annotation are typically those that needs to be byte-code manipulated. This includes final classes,
// classes with final, private, static or native methods that should be mocked and also classes that should be return
// a mock object upon instantiation.
@RunWith(PowerMockRunner.class)
@PrepareForTest({AttributeForFilterRepositoryBean.class})
public class AttributeForFilterRepositoryBeanTest {

    @InjectMocks
    AttributeForFilterRepositoryBean attrForFilterRepoBean;

    @Mock
    SortFieldList<AttributeForFilterSortField> sortFields;

    @Mock
    AttributeForFilterMapper attributeForFilterMapper;

    @Mock
    List<AttributeForFilter> attributes;

    @Mock
    AttributeForFilterFilter filter;

    @Mock
    PagingParams pagedParams;

    @Mock
    PagedResult expected;

    @Mock
    Page page;

    @Before
    public void setUp() throws Exception {
        when(attributeForFilterMapper.getInquiryAttributesForFilter(filter, page, sortFields)).thenReturn(attributes);
        when(attributeForFilterMapper.getCommonFaultAttributesForFilter(filter, page, sortFields)).thenReturn(attributes);

        when(pagedParams.getPage()).thenReturn(page);
        when(pagedParams.isCalculateTotalCount()).thenReturn(true);
        when(attributeForFilterMapper.countInquiryAttributesForFilter(filter)).thenReturn(100L);
        when(attributeForFilterMapper.countCommonFaultAttributesForFilter(filter)).thenReturn(100L);

        whenNew(PagedResult.class).withArguments(eq(page), eq(attributes), eq(100L)).thenReturn(expected);
    }

    @Test
    public void getInquiryAttributesForFilter() throws Exception {
        PagedResult result = attrForFilterRepoBean.getInquiryAttributesForFilter(filter, pagedParams, sortFields);
        assertEquals("Should be true!", expected, result);
    }

    @Test
    public void getCommonFaultAttributesForFilter() throws Exception {
        PagedResult result = attrForFilterRepoBean.getCommonFaultAttributesForFilter(filter, pagedParams, sortFields);
        assertEquals("Should be true!", expected, result);
    }

}