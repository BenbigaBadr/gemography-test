package com.gemography.trendingrepos.mapper;

import com.gemography.trendingrepos.Mapper.ItemMapper;
import com.gemography.trendingrepos.Mapper.ItemMapperImpl;
import com.gemography.trendingrepos.entity.ItemEntity;
import com.gemography.trendingrepos.entity.OwnerEntity;
import com.gemography.trendingrepos.models.Item;
import com.gemography.trendingrepos.models.Owner;
import com.gemography.trendingrepos.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class ItemMapperTest {

    private ItemMapper itemMapper = ItemMapper.INSTANCE;

    private ItemEntity itemEntity,itemEntity1;

    private Item item,item1;

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

        itemEntity1.setId(2l);
        itemEntity1.setName("name 2");
        itemEntity1.setHtml_url("htmlUrl_2");
        itemEntity1.setLanguage("Java");
        itemEntity1.setOwner(ownerEntity);

        item = new Item();

        Owner owner = new Owner();
        owner.setLogin("login1");
        owner.setType("type1");
        item.setName("name 1");
        item.setHtml_url("htmlUrl_1");
        item.setLanguage("C++");
        item.setOwner(owner);

        item1 = new Item();

        item1.setName("name 2");
        item1.setHtml_url("htmlUrl_2");
        item1.setLanguage("Java");
        item1.setOwner(owner);
    }

    @Test
    public void testDtoMapping()
    {
        Item item = itemMapper.sourceToDestination(itemEntity);
        Assert.assertEquals("name 1",item.getName());
        Assert.assertEquals("htmlUrl_1",item.getHtml_url());
        Assert.assertEquals("C++",item.getLanguage());
        Assert.assertEquals("login1",item.getOwner().getLogin());
        Assert.assertEquals("type1",item.getOwner().getType());
    }

    @Test
    public void testEntityMapping()
    {
        ItemEntity entity = itemMapper.destinationToSource(item);
        Assert.assertEquals("name 1",entity.getName());
        Assert.assertEquals("htmlUrl_1",entity.getHtml_url());
        Assert.assertEquals("C++",entity.getLanguage());
        Assert.assertEquals("login1",entity.getOwner().getLogin());
        Assert.assertEquals("type1",entity.getOwner().getType());
    }

    @Test
    public void testListEntityMapping()
    {
        List<ItemEntity> entities = new ArrayList<>();
        entities.addAll(Arrays.asList(itemEntity,itemEntity1));

        List<Item> items = itemMapper.sourceToDestination(entities);
        Assert.assertEquals(2, items.size());
    }

    @Test
    public void testListDtoMapping()
    {
        List<Item> dtos = new ArrayList<>();
        dtos.addAll(Arrays.asList(item,item1));

        List<ItemEntity> items = itemMapper.destinationToSource(dtos);
        Assert.assertEquals(2, items.size());
    }
}
