package com.gemography.trendingrepos.service;

import com.gemography.trendingrepos.Mapper.ItemMapper;
import com.gemography.trendingrepos.Mapper.ItemMapperImpl;
import com.gemography.trendingrepos.entity.ItemEntity;
import com.gemography.trendingrepos.entity.OwnerEntity;
import com.gemography.trendingrepos.models.Item;
import com.gemography.trendingrepos.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrendingReposServiceTest {

    @InjectMocks
    private TrendingReposService trendingReposService;

    @Mock
    private ItemRepository itemRepository;


    ItemEntity itemEntity, itemEntity1, itemEntity3;

    @Before
    public void setUp()
    {
        itemEntity = new ItemEntity();

        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setLogin("login1");
        ownerEntity.setType("type1");
        ownerEntity.setId(1l);
        itemEntity.setId(1l);
        itemEntity.setName("name 1");
        itemEntity.setHtml_url("htmlUrl_1");
        itemEntity.setLanguage("C++");
        itemEntity.setOwner(ownerEntity);

        itemEntity1 = new ItemEntity();
        OwnerEntity ownerEntity1 = new OwnerEntity();
        ownerEntity1.setLogin("login2");
        ownerEntity1.setType("type2");
        ownerEntity1.setId(2l);
        itemEntity1.setId(2l);
        itemEntity1.setName("name 2");
        itemEntity1.setHtml_url("htmlUrl_2");
        itemEntity1.setLanguage("java");
        itemEntity1.setOwner(ownerEntity1);

        itemEntity3 = new ItemEntity();
        OwnerEntity ownerEntity3 = new OwnerEntity();
        ownerEntity3.setLogin("login3");
        ownerEntity3.setType("type3");
        ownerEntity3.setId(3l);
        itemEntity3.setId(3l);
        itemEntity3.setName("name 3");
        itemEntity3.setHtml_url("htmlUrl_3");
        itemEntity3.setLanguage("java");
        itemEntity3.setOwner(ownerEntity1);


    }

    @Test
    public void testGetLanguageRepos()
    {
        when(itemRepository.getAllLanguages()).thenReturn(Arrays.asList("java","C++"));
        when(itemRepository.countByLanguage("java")).thenReturn(2l);
        when(itemRepository.countByLanguage("C++")).thenReturn(1l);
        when(itemRepository.findByLanguage("java")).thenReturn(Arrays.asList(itemEntity1, itemEntity3));
        when(itemRepository.findByLanguage("C++")).thenReturn(Arrays.asList(itemEntity));

        Map<String, List<Item>> itemsPerLangage = trendingReposService.getLanguageRepos();
        Assert.assertEquals(2, itemsPerLangage.get("java").size());
        Assert.assertEquals(1, itemsPerLangage.get("C++").size());
        Assert.assertEquals("name 1",((Item)itemsPerLangage.get("C++").get(0)).getName());
        Assert.assertEquals("htmlUrl_1",((Item)itemsPerLangage.get("C++").get(0)).getHtml_url());
    }

    @Test
    public void getLanguagesCount()
    {
        when(itemRepository.getAllLanguages()).thenReturn(Arrays.asList("java","C++"));
        when(itemRepository.countByLanguage("java")).thenReturn(2l);
        when(itemRepository.countByLanguage("C++")).thenReturn(1l);
        when(itemRepository.findByLanguage("java")).thenReturn(Arrays.asList(itemEntity1, itemEntity3));
        when(itemRepository.findByLanguage("C++")).thenReturn(Arrays.asList(itemEntity));

        Map<String, Long> itemsPerLangage = trendingReposService.getLanguagesCount();
        Assert.assertEquals(Long.valueOf(2l),(Long) itemsPerLangage.get("java"));
        Assert.assertEquals(Long.valueOf(1l), (Long) itemsPerLangage.get("C++"));
    }
}
