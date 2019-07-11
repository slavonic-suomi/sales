package by.gsu.study.sales.core.menu.common;

import by.gsu.study.sales.core.entity.Product;
import by.gsu.study.sales.core.factory.IFactory;
import by.gsu.study.sales.core.repository.IRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CommonAddMenuItemTest {

    @Mock
    IFactory<Product> factory;

    @Mock
    IRepository<Product> repository;

    @InjectMocks
    CommonAddMenuItem<Product> testee;

    Product product = null;

    @Before
    public void init() {
        when(factory.create())
//                .thenReturn(new Product());
            .thenAnswer((Answer<Product>) invocation -> {
                product = new Product(null, "testProduct");
                return product;
            });

//        when(repository).thenAnswer(new Answer<Product>() {
//            @Override
//            public Product answer(InvocationOnMock invocation) throws Throwable {
//                return null;
//            }
//        }).

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Product argument = invocation.getArgument(0);
                argument.setId(1);
                return null;
            }
        }).when(repository).save(any(Product.class));


    }

    @Test
    public void execute() {
        testee.execute();

        verify(factory, only()).create();

        verify(repository, only()).save(any(Product.class));

        assertNotNull(product);
        assertNotNull(product.getId());
    }
}